package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.AoCSolution
import emiliomg.aoc.y2024.util.StringUtil.asStringList
import kotlin.math.abs

object Day1 : AoCSolution<Long, Long> {
    override fun star1(raw: String): Long {
        val (left, right) = fetchAndConvert(raw)

        val result = left.zip(right).sumOf { (a, b) ->
            abs(a - b)
        }

        return result
    }

    override fun star2(raw: String): Long {
        val (left, right) = fetchAndConvert(raw)

        val result = left.fold(0L) { acc, elem ->
            acc + (elem * right.count { it == elem })
        }

        return result
    }

    private fun fetchAndConvert(raw: String): Pair<List<Long>, List<Long>> {
        val result = raw
            .asStringList()
            .map { line: String ->
                val elems = line.split("   ")
                elems[0].toLong() to elems[1].toLong()
            }
            .unzip()
            .run {
                first.sorted() to second.sorted()
            }

        return result
    }

}