package emiliomg.aoc.y2024.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PositionUtilTest : FunSpec({
    context("Matrix") {
        context("charAt") {
            context("finds correct chars") {
                withData(
                    PositionUtil.Point(0, 0) to '1',
                    PositionUtil.Point(1, 0) to '2',
                    PositionUtil.Point(0, 1) to '4',
                    PositionUtil.Point(2, 2) to '9',
                ) { (p, expectation) ->
                    testMatrix.charAt(p) shouldBe expectation
                }
            }

            test("returns correct default char if out of bounds") {
                testMatrix.charAt(PositionUtil.Point(3, 3)) shouldBe '.'
                testMatrix.charAt(PositionUtil.Point(3, 3), 'X') shouldBe 'X'
            }
        }

        context("hasInBounds") {
            context("checks for boundary") {
                withData(
                    PositionUtil.Point(0, 0) to true,
                    PositionUtil.Point(1, 1) to true,
                    PositionUtil.Point(3, 3) to false,
                    PositionUtil.Point(-1, -1) to false,
                    PositionUtil.Point(-999, 999) to false,
                ) { (p, expectation) ->
                    testMatrix.hasInBounds(p) shouldBe expectation
                }
            }
        }
    }
})

private val testMatrix = PositionUtil.Matrix.fromInput("123\n456\n789")