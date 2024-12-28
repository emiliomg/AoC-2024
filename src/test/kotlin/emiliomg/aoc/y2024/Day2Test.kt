package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.testutil.TestUtil
import io.kotest.core.spec.style.FunSpec

class Day2Test : FunSpec({
    include(
        TestUtil.AoCTest(
        solutionObject = Day2,
        examplesStar1 = listOf(
            "day2/example.txt" to 2
        ),
        puzzleStar1 = "day2/input.txt" to 371,
        examplesStar2 = listOf(
            "day2/example.txt" to 4,
            "day2/edgecases.txt" to 10,
        ),
        puzzleStar2 = "day2/input.txt" to 426
    ))
})
