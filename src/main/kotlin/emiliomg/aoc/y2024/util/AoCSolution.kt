package de.emiliomg.aoc.y2024.util

interface AoCSolution<S1, S2> {
    fun star1(raw: String): S1
    fun star2(raw: String): S2
}

fun String.asStringList(): List<String> = this.split("\n")

fun <T>String.asProcessedList(transform: String.() -> T): List<T> =
    this
        .asStringList()
        .map { it.transform() }

//fun List<String>.head(): Pair<String, List<String>> = Pair(take(1).first(), drop(1))