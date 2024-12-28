package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.testutil.TestUtil
import io.kotest.core.spec.style.FunSpec

class _DayXTest : FunSpec({
    include(
        TestUtil.AoCTest(
            solutionObject = _DayX,
            examplesStar1 = listOf(
                "dayX/example.txt" to 123
            ),
//            puzzleStar1 = "dayX/input.txt" to 123,
//            examplesStar2 = listOf("dayX/example.txt" to 123),
//            puzzleStar2 = "dayX/input.txt" to 123
        )
    )
})
