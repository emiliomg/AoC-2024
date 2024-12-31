package emiliomg.aoc.y2024

import emiliomg.aoc.y2024.util.AoCSolution
import emiliomg.aoc.y2024.util.StringUtil.asStringList

private typealias Page = List<Int>
private typealias Ordering = List<Pair<Int, Int>>

object Day5 : AoCSolution<Long, Long> {
    override fun star1(raw: String): Long {
        val (ordering: Ordering, pages: List<Page>) = fetchAndPrepareData(raw)

        val validPages = pages.mapNotNull {
            if (it.isValidAccordingTo(ordering)) it else null
        }

        val result = validPages.sumOf { it[it.size / 2].toLong() }

        return result
    }

    override fun star2(raw: String): Long {
        val (ordering: Ordering, pages: List<Page>) = fetchAndPrepareData(raw)

        val invalidPages = pages.mapNotNull {
            if (it.isValidAccordingTo(ordering)) null else it
        }

        val fixedPages = invalidPages.map { it.sortedWith { x, y -> ordering.order(x, y) }}

        val result = fixedPages.sumOf { it[it.size / 2].toLong() }

        return result
    }

    /*
     * Assumption: The orders defined in the ordering are describe direct pairs,
     * e.g. (1, 5) means (1 comes _directly_ before 5)
     */
    private fun Ordering.order(a: Int, b: Int): Int {
        if (this.contains(a to b)) return 1
        if (this.contains(b to a)) return -1

        TODO("This should not happen")
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

    private fun fetchAndPrepareData(raw: String): Pair<List<Pair<Int, Int>>, List<Page>> {
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
        return Pair(ordering, pages)
    }
}