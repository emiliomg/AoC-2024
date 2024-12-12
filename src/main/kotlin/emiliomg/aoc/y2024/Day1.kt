package emiliomg.aoc.y2024

import de.emiliomg.aoc.y2024.util.AoCSolution
import de.emiliomg.aoc.y2024.util.asStringList
import kotlin.math.abs

object Day1 : AoCSolution<Long, Long> {
    override fun star1(raw: String): Long {
        val (left, right) = raw
            .asStringList()
            .map { line: String ->
                val elems = line.split("   ")
                elems[0].toLong() to elems[1].toLong()
            }
            .unzip()
            .run {
                first.sorted() to second.sorted()
            }

        val result = left.zip(right).sumOf { (a, b) ->
            abs(a - b)
        }

        return result
    }

    override fun star2(raw: String): Long {
        TODO()
    }
}