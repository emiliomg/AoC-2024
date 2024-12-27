package emiliomg.aoc.y2024

import de.emiliomg.aoc.y2024.util.AoCSolution
import de.emiliomg.aoc.y2024.util.asProcessedList
import kotlin.math.abs

typealias Report = List<Long>

object Day2 : AoCSolution<Int, Long> {
    override fun star1(raw: String): Int {
        val reports: List<Report> = fetchAndPrepareData(raw)

        val result = reports
            .mapNotNull { report -> // Remove reports that are not strictly ascending / descending
                val sorted = report.distinct().sorted()
                if (report == sorted || report == sorted.reversed() ) {
                    report
                } else {
                    null
                }
            }
            .mapNotNull { report -> // Remove reports that are "changing too fast"
                val changesCorrectly = report
                    .zipWithNext()
                    .map { (a, b) ->
                        abs(a - b) in 1..3
                    }
                    .all { it }
                if (changesCorrectly) report else null
            }
            .count()

        return result
    }

    override fun star2(raw: String): Long {
        val reports: List<Report> = fetchAndPrepareData(raw)
        val safeReports: Map<String, Boolean> =
            reports.associate { it.joinToString() to stepThroughReport(it, 0, Direction.UNDECIDED, false) }

        println(safeReports)

        val result = safeReports.count { it.value }.toLong()
        return result
    }

    private fun stepThroughReport(report: Report, idx: Int, direction: Direction, dampenerAlreadyEngaged: Boolean): Boolean {
        if (idx + 1 == report.size) return true

        val (a, b) = report[idx] to report [idx + 1]
        val hasRightDistance = abs(a - b) in 1..3
        val (isKeepingDirection: Boolean, newDirection: Direction) = when(direction) {
            Direction.UNDECIDED -> if (a < b) true to Direction.ASC else true to Direction.DESC
            Direction.ASC -> if (a < b) true to Direction.ASC else false to Direction.ASC
            Direction.DESC -> if (a < b) false to Direction.DESC else true to Direction.DESC
        }

        val isValid = hasRightDistance && isKeepingDirection

        if (isValid) {
            return stepThroughReport(report, idx + 1, newDirection, dampenerAlreadyEngaged)
        } else {
            if (dampenerAlreadyEngaged) return false

            val reportDroppedPreviousElement: Report? = if (idx == 0) null else report.filterIndexed { index, _ -> index != idx - 1 }
            val reportDroppedCurrentElement: Report = report.filterIndexed { index, _ -> index != idx }
            val reportDroppedNextElement: Report = report.filterIndexed { index, _ -> index != idx + 1 }

            val isFixedDroppedPrevious =  reportDroppedPreviousElement?.let {
                stepThroughReport(it, 0, Direction.UNDECIDED, true)
            } ?: false
            val isFixedDroppedCurrent = stepThroughReport(reportDroppedCurrentElement, 0, Direction.UNDECIDED, true)
            val isFixedDroppedNext = stepThroughReport(reportDroppedNextElement, 0, Direction.UNDECIDED, true)

            return isFixedDroppedPrevious || isFixedDroppedCurrent || isFixedDroppedNext
        }
    }

    private enum class Direction {
        ASC, DESC, UNDECIDED;
    }

    private fun fetchAndPrepareData(raw: String) = raw.asProcessedList {
        this.split(" ").map { it.toLong() }
    }
}