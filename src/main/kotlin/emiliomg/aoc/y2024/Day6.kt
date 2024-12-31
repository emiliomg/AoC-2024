package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.AoCSolution
import emiliomg.aoc.y2024.util.PositionUtil.Point
import emiliomg.aoc.y2024.util.PositionUtil.Direction
import emiliomg.aoc.y2024.util.PositionUtil.Matrix

object Day6 : AoCSolution<Int, Int> {
    override fun star1(raw: String): Int {
        val matrix = Matrix.fromInput(raw)
        val startingGuard = Guard(matrix.findChar('^').first(), Direction.NORTH)

        // It is assumed that the current position is already part of the "step" list
        tailrec fun step(guard: Guard, steps: List<Point>): List<Point> {
            val nextPosition = guard.position + guard.direction

            if (!matrix.hasInBounds(nextPosition)) return steps

            if (matrix.charAt(nextPosition) == '#')
                return step(guard.turnRight(), steps)

            val newGuard = guard.moveForward()

            return step(newGuard, steps + newGuard.position)
        }

        val visitedPoints = step(startingGuard, listOf(startingGuard.position))

        return visitedPoints.toSet().size
    }

    override fun star2(raw: String): Int {
        TODO()
    }

    private data class Guard(val position: Point, val direction: Direction) {
        fun turnRight(): Guard = Guard(position, direction.right90())
        fun moveForward(): Guard = Guard(position + direction, direction)
    }
}