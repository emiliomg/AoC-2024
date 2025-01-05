package emiliomg.aoc.y2024.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import emiliomg.aoc.y2024.util.PositionUtil.Point
import emiliomg.aoc.y2024.util.PositionUtil.Matrix
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class PositionUtilTest : FunSpec({
    context("Matrix") {
        context("charAt") {
            context("finds correct chars") {
                withData(
                    Point(0, 0) to '1',
                    Point(1, 0) to '2',
                    Point(0, 1) to '4',
                    Point(2, 2) to '9',
                ) { (p, expectation) ->
                    testMatrix.charAt(p) shouldBe expectation
                }
            }

            test("returns correct default char if out of bounds") {
                testMatrix.charAt(Point(3, 3)) shouldBe '.'
                testMatrix.charAt(Point(3, 3), 'X') shouldBe 'X'
            }
        }

        context("hasInBounds") {
            context("checks for boundary") {
                withData(
                    Point(0, 0) to true,
                    Point(1, 1) to true,
                    Point(3, 3) to false,
                    Point(-1, -1) to false,
                    Point(-999, 999) to false,
                ) { (p, expectation) ->
                    testMatrix.hasInBounds(p) shouldBe expectation
                }
            }
        }

        context ("findChar") {
            context("finds correct character") {
                withData(
                    nameFn = { it.first.toString() },
                    '1' to listOf(Point(0, 0), Point(1, 0)),
                    '2' to listOf(Point(2, 0), Point(0, 1)),
                    '5' to listOf(Point(2, 2)),
                    '9' to emptyList()
                ) { (p, expectation) ->
                    secondTestMatrix.findChar(p) shouldContainExactlyInAnyOrder expectation
                }
            }
        }

        context("replacePointWith") {
            test("creates a new matrix with a replaced point") {
                val checkMe = Point(1, 2)
                val newMatrix = testMatrix.replacePointWith(checkMe, 'X')

                newMatrix.charAt(checkMe) shouldBe 'X'
            }
        }
    }
})

private val testMatrix = Matrix.fromInput("123\n456\n789")
private val secondTestMatrix = Matrix.fromInput("112\n233\n445")