package emiliomg.aoc.y2024.util

object CollectionUtil {
    fun <T> List<T>.headAndTail(): Pair<T, List<T>> = first() to drop(1)
}