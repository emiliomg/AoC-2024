package emiliomg.aoc.y2024.testutil

import emiliomg.aoc.y2024.util.AoCSolution
import io.kotest.core.spec.style.funSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

object TestUtil {
    fun <S1, S2>AoCTest(
        solutionObject: AoCSolution<S1, S2>,
        examplesStar1: List<Pair<String, S1>>? = null,
        puzzleStar1: Pair<String, S2>? = null,
        examplesStar2: List<Pair<String, S2>>? = null,
        puzzleStar2: Pair<String, S2>? = null
    ) = funSpec {
        context("Star1") {
            if(examplesStar1 != null) {
                context("Example") {
                    withData(examplesStar1) { (exampleInput: String, expectedResult: S1) ->
                        val data = if(exampleInput.endsWith(".txt")) {
                            getInput(exampleInput)
                        } else exampleInput
                        (solutionObject::star1)(data) shouldBe expectedResult
                    }
                }
            }
            if(puzzleStar1 != null) {
                test("Puzzle") {
                    val (inputFile, expectedResult) = puzzleStar1
                    val data = getInput(inputFile)
                    (solutionObject::star1)(data) shouldBe expectedResult
                }
            }
        }

        context("Star2") {
            if(examplesStar2 != null) {
                context("Example") {
                    withData(examplesStar2) { (exampleInput, expectedResult) ->
                        val data = if(exampleInput.endsWith(".txt")) {
                            getInput(exampleInput)
                        } else exampleInput
                        (solutionObject::star2)(data) shouldBe expectedResult
                    }
                }
            }
            if(puzzleStar2 != null) {
                test("Puzzle") {
                    val (inputFile, expectedResult) = puzzleStar2
                    val data = getInput(inputFile)
                    (solutionObject::star2)(data) shouldBe expectedResult
                }
            }
        }
    }

    private fun getInput(rawPath: String): String {
        val path = if(rawPath.startsWith("/")) rawPath else "/$rawPath"
        val data = object {}.javaClass.getResource(path)?.readText() ?: throw Exception("File $path not found!")

        return data
    }
}