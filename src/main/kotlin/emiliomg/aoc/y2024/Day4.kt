package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.AoCSolution
import emiliomg.aoc.y2024.util.PositionUtil
import emiliomg.aoc.y2024.util.PositionUtil.Matrix

object Day4 : AoCSolution<Int, Int> {
    override fun star1(raw: String): Int {
        val matrix = Matrix.fromInput(raw)

        val result: List<Int> = matrix.map { point, char ->
            if (char != 'X') {
                0
            } else {
                val xmasStringsStartingFromThisPosition = PositionUtil.Direction.entries.map { direction ->
                    val directionalString = (1..3).fold(point to emptyList<Char>()) { (currentPoint, acc), _ ->
                        val newPoint = currentPoint + direction
                        newPoint to acc + matrix.charAt(newPoint)
                    }

                    val joinedDirectionalString = directionalString.second.joinToString(separator = "")
                    if (joinedDirectionalString == "MAS") 1 else 0
                }.sum()

                xmasStringsStartingFromThisPosition
            }

        }

        return result.sum()
    }

    override fun star2(raw: String): Int {
        val matrix = Matrix.fromInput(raw)

        val result: List<Int> = matrix.map { point, char ->
            if (char != 'A') {
                0
            } else {
                val xmasStringCenteredHere = PositionUtil.InterCardinalDirections.map { directionToCheck ->
                    val startingPoint = point + directionToCheck.opposite()

                    val directionalString = (1..2).fold(startingPoint to listOf<Char>(matrix.charAt(startingPoint))) { (currentPoint, acc), _ ->
                        val newPoint = currentPoint + directionToCheck
                        newPoint to acc + matrix.charAt(newPoint)
                    }

                    val joinedDirectionalString = directionalString.second.joinToString(separator = "")

                    if (joinedDirectionalString == "MAS") 1 else 0
                }.sum()

                if (xmasStringCenteredHere == 2) 1 else 0
            }

        }

        return result.sum()

    }
}