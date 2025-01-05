package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.testutil.TestUtil
import io.kotest.core.spec.style.FunSpec

class Day7Test : FunSpec({
    include(
        TestUtil.AoCTest(
            solutionObject = Day7,
            examplesStar1 = listOf(
                "day7/example.txt" to 3749
            ),
            puzzleStar1 = "day7/input.txt" to 4555081946288,
            examplesStar2 = listOf("day7/example.txt" to 11387),
            puzzleStar2 = "day7/input.txt" to 227921760109726 // TODO >4 sek, langsam!
        )
    )
})
