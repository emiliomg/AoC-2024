package emiliomg.aoc.y2024.util

typealias Row = CharArray

object PositionUtil {

    class Matrix(val data: List<Row>) {
        fun charAt(p: Point, defaultChar: Char = '.'): Char =
            if (!hasInBounds(p))
                defaultChar
            else
                data[p.y][p.x]

        // This method assumes each line to have the same amount of elements
        // This assumption is not checked anywhere
        fun hasInBounds(p: Point): Boolean =
            p.x in data[0].indices &&
            p.y in data.indices

        fun <T> flatMap(f: (Point, Char) -> T): List<T> {
            return data.flatMapIndexed { y, line ->
                line.mapIndexed { x, c ->
                    f(Point(x, y), c)
                }
            }
        }

        companion object {
            fun fromInput(raw: String): Matrix {
                val data = raw
                    .split("\n")
                    .map { line -> line.toCharArray() }
                return Matrix(data)
            }
        }
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(direction: Direction): Point = Point(x + direction.modX, y + direction.modY)
    }

    enum class Direction(val modX: Int, val modY: Int) {
        NORTH(0, -1) { override fun opposite(): Direction = SOUTH },
        NORTHEAST(1, -1) { override fun opposite(): Direction = SOUTHWEST },
        EAST(1, 0) { override fun opposite(): Direction = WEST },
        SOUTHEAST(1, 1) { override fun opposite(): Direction = NORTHWEST },
        SOUTH(0, 1) { override fun opposite(): Direction = NORTH },
        SOUTHWEST(-1, 1) { override fun opposite(): Direction = NORTHEAST },
        WEST(-1, 0) { override fun opposite(): Direction = EAST },
        NORTHWEST(-1, -1) { override fun opposite(): Direction = SOUTHEAST };

        abstract fun opposite(): Direction
    }

    val CardinalDirections = listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
    val InterCardinalDirections = listOf(Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST)
}