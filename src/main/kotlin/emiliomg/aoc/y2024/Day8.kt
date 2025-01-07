package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.AoCSolution
import emiliomg.aoc.y2024.util.CollectionUtil.headAndTail
import emiliomg.aoc.y2024.util.PositionUtil.Point
import emiliomg.aoc.y2024.util.PositionUtil.Matrix

object Day8 : AoCSolution<Int, Int> {
    override fun star1(raw: String): Int {
        val matrix: Matrix = Matrix.fromInput(raw)
        val frequenciesToAntennas: Map<Char, List<Point>> = matrix.reverse()

        val antinodes = frequenciesToAntennas.mapValues { (freq, antennas) ->
            val antennaCombinations = antennas.flatMapIndexed { idx, _ ->
                val (head, tail) = antennas.drop(idx).headAndTail()
                tail.map { head to it }
            }

            val newAntinodes = antennaCombinations.flatMap { (p1, p2) ->
                val xDiff = p2.x - p1.x
                val yDiff = p2.y - p1.y

                val a1 = Point(p1.x - xDiff, p1.y - yDiff )
                val a2 = Point(p2.x + xDiff, p2.y + yDiff )

                listOf(a1, a2)
            }

            newAntinodes.filter { matrix.hasInBounds(it) }
        }

        return antinodes.values.flatten().toSet().size
    }

    override fun star2(raw: String): Int {
        TODO()
    }

}