package emiliomg.aoc.y2024.util

interface AoCSolution<S1, S2> {
    fun star1(raw: String): S1
    fun star2(raw: String): S2
}