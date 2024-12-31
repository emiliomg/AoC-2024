package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.AoCSolution
import emiliomg.aoc.y2024.util.StringUtil.asStringList

private typealias Page = List<Int>
private typealias Ordering = List<Pair<Int, Int>>

object Day5 : AoCSolution<Long, Long> {
    override fun star1(raw: String): Long {
        val data = raw.asStringList()
        val ordering = data
            .filter { it.contains('|') }
            .map { line ->
                val (a, b) = line.split('|')
                a.toInt() to b.toInt()
            }

        val pages: List<Page> = data
            .filter { it.contains(',') }
            .map { it.split(',').map { it.toInt() } }

        val validPages = pages.mapNotNull {
            if (it.isValidAccordingTo(ordering)) it else null
        }

        val result = validPages.map { it[it.size / 2].toLong()}.sum()

        return result
    }

    /*
     * No idea why IntelliJ says
     * Condition 'checkIndex > runIndex' is always true
     * This is not the case
     */
    private fun Page.isValidAccordingTo(ordering: Ordering): Boolean {
        val thePage = this
        val validPositions = thePage.mapIndexed { checkIndex, checkElement ->
            thePage.mapIndexed { runIndex, runElement ->
                when {
                    checkIndex < runIndex -> ordering.contains(checkElement to runElement)
                    checkIndex == runIndex -> true
                    checkIndex > runIndex -> ordering.contains(runElement to checkElement)
                    else -> TODO("This should not happen")
                }
            }
        }.flatten()

        return validPositions.all { it }
    }

    override fun star2(raw: String): Long {
        TODO()
    }
}