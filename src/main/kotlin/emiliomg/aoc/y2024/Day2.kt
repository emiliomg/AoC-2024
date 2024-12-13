package emiliomg.aoc.y2024

import de.emiliomg.aoc.y2024.util.AoCSolution
import de.emiliomg.aoc.y2024.util.asProcessedList
import kotlin.math.abs

typealias Report = List<Long>

object Day2 : AoCSolution<Int, Long> {
    override fun star1(raw: String): Int {
        val reports: List<Report> = raw.asProcessedList {
            this.split(" ").map { it.toLong() }
        }

        println(reports)

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
        TODO()
    }
}