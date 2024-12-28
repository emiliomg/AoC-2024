package emiliomg.aoc.y2024

import de.emiliomg.aoc.y2024.util.AoCSolution
import de.emiliomg.aoc.y2024.util.asStringList

object Day3 : AoCSolution<Long, Long> {
    override fun star1(raw: String): Long {
        val line = raw.asStringList().joinToString(separator = "")
        val findCommand: Regex = "(mul\\(\\d+,\\d+\\))+?".toRegex()
        val extractValue: Regex = "mul\\((\\d+),(\\d+)\\)".toRegex()

        val matches: List<MatchResult> = findCommand.findAll(line).toList()
        val result = matches.sumOf { mulCommand ->
            val (a, b) = extractValue.matchEntire(mulCommand.value)!!.groupValues.takeLast(2)
            a.toLong() * b.toLong()
        }

        return result
    }

    override fun star2(raw: String): Long {
        val line = raw.asStringList().joinToString(separator = "")
        val findCommand: Regex = "(mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\))+?".toRegex()
        val extractValue: Regex = "mul\\((\\d+),(\\d+)\\)".toRegex()

        val commands = findCommand.findAll(line).toList().map { it.value }
        val result = commands.fold(Pair(0L, false)) { (acc, suspendMul), cmd ->
            when (cmd) {
                "do()" -> acc to false
                "don't()" -> acc to true
                else -> when(suspendMul) {
                    true -> acc to true
                    false -> {
                        val (a, b) = extractValue.matchEntire(cmd)!!.groupValues.takeLast(2)
                        acc + (a.toLong() * b.toLong()) to false
                    }
                }
            }
        }

        return result.first
    }
}