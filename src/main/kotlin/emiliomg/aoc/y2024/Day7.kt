package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.AoCSolution
import emiliomg.aoc.y2024.util.StringUtil.asProcessedList

object Day7 : AoCSolution<Long, Long> {
    override fun star1(raw: String): Long {
        val equations: List<Pair<Long, List<Long>>> = fetchAndPrepareData(raw)
        return processEquations(equations, OPRT.PART1)
    }

    override fun star2(raw: String): Long {
        val equations: List<Pair<Long, List<Long>>> = fetchAndPrepareData(raw)
        return processEquations(equations, OPRT.PART2)
    }

    private fun processEquations(equations: List<Pair<Long, List<Long>>>, allowedOperators: List<OPRT>): Long {
        fun step(nums: List<Long>, ops: List<OPRT>, acc: Long): List<Long> {
            if (nums.isEmpty()) return listOf(acc)

            val (head, tail) = nums.first() to nums.drop(1)

            return ops.flatMap { op ->
                val newAcc = op(acc, head)
                step(tail, ops, newAcc)
            }
        }

        val validTestValues = equations.mapNotNull { (expected, nums) ->
            if (step(nums, allowedOperators, 0).contains(expected)) expected
            else null
        }

        return validTestValues.sum()
    }

    private fun fetchAndPrepareData(raw: String): List<Pair<Long, List<Long>>> =
        raw.asProcessedList { line ->
            val (target, partStr) = line.split(": ")
            val parts = partStr.split(" ").map { it.toLong() }

            target.toLong() to parts
        }

    private enum class OPRT {
        ADD { override operator fun invoke(a: Long, b: Long): Long { return a + b } },
        MULT { override operator fun invoke(a: Long, b: Long): Long { return a * b } },
        CONCAT { override operator fun invoke(a: Long, b: Long): Long { return (a.toString() + b.toString()).toLong() } };

        abstract operator fun invoke(a: Long, b: Long): Long

        companion object {
            val PART1 = listOf(ADD, MULT)
            val PART2 = listOf(ADD, MULT, CONCAT)
        }
    }
}