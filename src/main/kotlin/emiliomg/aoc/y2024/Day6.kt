package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.AoCSolution
import emiliomg.aoc.y2024.util.PositionUtil.Point
import emiliomg.aoc.y2024.util.PositionUtil.Direction
import emiliomg.aoc.y2024.util.PositionUtil.Matrix

object Day6 : AoCSolution<Int, Int> {

    private const val OBSTACLE = '#'
    private const val STARTINGGUARD = '^'

    override fun star1(raw: String): Int {
        val (matrix, startingGuard) = fetchAndPrepareData(raw)

        val (visitedGuardPositions, _) = getVisitedPointsInMaze(matrix, startingGuard, listOf(startingGuard))

        return visitedGuardPositions.map { it.position }.toSet().size
    }

    override fun star2(raw: String): Int {
        val (matrix, startingGuard) = fetchAndPrepareData(raw)

        val possiblePointsForNewObstacle = getVisitedPointsInMaze(matrix, startingGuard, listOf(startingGuard))
            .allPositions
            .minus(startingGuard.position)

        val pointsForNewObstacle: Map<Point,Boolean> =
            possiblePointsForNewObstacle.associateWith { newObstaclePosition ->
                val newMatrix = matrix.replacePointWith(newObstaclePosition, OBSTACLE)
                val (_, isLoop) = getVisitedPointsInMaze(newMatrix, startingGuard, listOf(startingGuard))

                isLoop
            }.filterValues { it }

        return pointsForNewObstacle.size
    }

    private data class MazeResult(val guardPositions: List<Guard>, val loopFound: Boolean) {
        val allPositions: Set<Point> = guardPositions.map { it.position }.toSet()
    }

    // It is assumed that the current position is already part of the "step" list
    private tailrec fun getVisitedPointsInMaze(matrix: Matrix, guard: Guard, steps: List<Guard>): MazeResult {
        val nextGuard = guard.copy(position = guard.position + guard.direction)

        if (!matrix.hasInBounds(nextGuard.position)) return MazeResult(steps, false)

        if (matrix.charAt(nextGuard.position) == OBSTACLE)
            return getVisitedPointsInMaze(matrix, guard.turnRight(), steps)

        if (steps.contains(nextGuard))
            return MazeResult(steps, true)

        val newGuard = guard.moveForward()

        return getVisitedPointsInMaze(matrix, newGuard, steps + newGuard)
    }

    private fun fetchAndPrepareData(raw: String): Pair<Matrix, Guard> {
        val matrix = Matrix.fromInput(raw)
        val startingGuard = Guard(matrix.findChar(STARTINGGUARD).first(), Direction.NORTH)
        return Pair(matrix, startingGuard)
    }

    private data class Guard(val position: Point, val direction: Direction) {
        fun turnRight(): Guard = copy(direction = direction.right90())
        fun moveForward(): Guard = copy(position = position + direction)
    }
}