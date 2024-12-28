package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.testutil.TestUtil
import io.kotest.core.spec.style.FunSpec

class Day1Test : FunSpec({
    include(
        TestUtil.AoCTest(
            solutionObject = Day1,
            examplesStar1 = listOf(
                "day1/example.txt" to 11
            ),
            puzzleStar1 = "day1/input.txt" to 2192892L,
            examplesStar2 = listOf("day1/example.txt" to 31),
            puzzleStar2 = "day1/input.txt" to 22962826L
        )
    )
})
