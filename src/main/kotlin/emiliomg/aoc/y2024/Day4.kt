package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.AoCSolution
import emiliomg.aoc.y2024.util.PositionUtil
import emiliomg.aoc.y2024.util.PositionUtil.Matrix

object Day4 : AoCSolution<Int, Int> {
    override fun star1(raw: String): Int {
        val matrix = Matrix.fromInput(raw)

        val result: List<Int> = matrix.flatMap { point, char ->
            if (char != 'X') {
                0
            } else {
                val xmasStringsStartingFromThisPosition = PositionUtil.Direction.entries.map { direction ->
                    val directionalString = (1..3).fold(point to emptyList<Char>()) { (currentPoint, acc), _ ->
                        val newPoint = currentPoint + direction
                        newPoint to acc + matrix.charAt(newPoint)
                    }

                    if (directionalString.second.joinToString(separator = "") == "MAS") 1 else 0
                }.sum()

                xmasStringsStartingFromThisPosition
            }

        }

        return result.sum()
    }

    override fun star2(raw: String): Int {
        TODO()
    }
}