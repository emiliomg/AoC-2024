package emiliomg.aoc.y2024.util

import kotlin.String

object StringUtil {
    fun String.asStringList(): List<String> = this.split("\n")

    fun <T> String.asProcessedList(lineProcessor: (String) -> T): List<T> =
        this
            .asStringList()
            .map { lineProcessor(it) }
}