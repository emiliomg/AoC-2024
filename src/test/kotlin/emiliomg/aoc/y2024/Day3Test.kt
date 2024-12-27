package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.TestUtil
import io.kotest.core.spec.style.FunSpec

class Day3Test : FunSpec({
    include(TestUtil.AoCTest(
        solutionObject = Day3,
        examplesStar1 = listOf(
            "day3/example.txt" to 161
        ),
        puzzleStar1 = "day3/input.txt" to 164730528L,
//        examplesStar2 = listOf("day3/example.txt" to 123),
//        puzzleStar2 = "day3/input.txt" to 123
    ))
})
