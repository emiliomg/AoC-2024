package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.testutil.TestUtil
import io.kotest.core.spec.style.FunSpec

class Day6Test : FunSpec({
    include(
        TestUtil.AoCTest(
            solutionObject = Day6,
            examplesStar1 = listOf(
                "day6/example.txt" to 41
            ),
            puzzleStar1 = "day6/input.txt" to 5145,
//            examplesStar2 = listOf("day6/example.txt" to 123),
//            puzzleStar2 = "day6/input.txt" to 123
        )
    )
})
