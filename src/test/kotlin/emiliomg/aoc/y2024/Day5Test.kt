package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.testutil.TestUtil
import io.kotest.core.spec.style.FunSpec

class Day5Test : FunSpec({
    include(
        TestUtil.AoCTest(
            solutionObject = Day5,
            examplesStar1 = listOf(
                "day5/example.txt" to 143
            ),
            puzzleStar1 = "day5/input.txt" to 5087,
            examplesStar2 = listOf("day5/example.txt" to 123),
            puzzleStar2 = "day5/input.txt" to 4971
        )
    )
})
