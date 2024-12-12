package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.TestUtil
import io.kotest.core.spec.style.FunSpec

class _DayXTest : FunSpec({
    include(TestUtil.AoCTest(
        solutionObject = Day1,
        examplesStar1 = listOf(
            "dayX/exampleY.txt" to 123
        ),
//        puzzleStar1 = "dayX/input.txt" to 123,
//        examplesStar2 = listOf("dayX/exampleY.txt" to 123),
//        puzzleStar2 = "dayX/input.txt" to 123
    ))
})
