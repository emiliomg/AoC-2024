package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.testutil.TestUtil
import io.kotest.core.spec.style.FunSpec

class Day4Test : FunSpec({
    include(
        TestUtil.AoCTest(
        solutionObject = Day4,
        examplesStar1 = listOf(
            "day4/example.txt" to 18
        ),
        puzzleStar1 = "day4/input.txt" to 2642,
        examplesStar2 = listOf("day4/example.txt" to 9),
//        puzzleStar2 = "day4/input.txt" to 123
    ))
})
