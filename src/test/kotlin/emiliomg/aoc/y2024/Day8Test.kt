package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.testutil.TestUtil
import io.kotest.core.spec.style.FunSpec

class Day8Test : FunSpec({
    include(
        TestUtil.AoCTest(
            solutionObject = Day8,
            examplesStar1 = listOf(
                "day8/example_simple1.txt" to 2,
                "day8/example_simple2.txt" to 3,
                "day8/example_simple3.txt" to 4,
                "day8/example.txt" to 14,
            ),
            puzzleStar1 = "day8/input.txt" to 318,
//            examplesStar2 = listOf("day8/example.txt" to 123),
//            puzzleStar2 = "day8/input.txt" to 123
        )
    )
})
