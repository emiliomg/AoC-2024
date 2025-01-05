package emiliomg.aoc.y2024.util

object PositionUtil {

    class Matrix(val data: HashMap<Point, Char>) {
        fun charAt(p: Point, defaultChar: Char = EMPTY): Char =
            data[p] ?: defaultChar

        fun findChar(searchMe: Char): List<Point> =
            data.filter { (_, c) -> c == searchMe }.keys.toList() // todo convert to set

        // This method assumes each line to have the same amount of elements
        // This assumption is not checked anywhere
        fun hasInBounds(p: Point): Boolean =
            data.contains(p)

        fun replacePointWith(point: Point, replaceWith: Char): Matrix {
            val newData = HashMap(data)
            newData[point] = replaceWith

            return Matrix(newData)
        }

        fun <T> map(f: (Point, Char) -> T): List<T> =
            data.map { (p, c) -> f(p, c) }

        companion object {

            const val EMPTY = '.'

            fun fromInput(raw: String): Matrix {
                val data = raw
                    .split("\n")
                    .map { line -> line.toCharArray() }

                val points = data.flatMapIndexed { y, line ->
                    line.mapIndexed { x, c ->
                        Point(x, y) to c
                    }
                }.toMap()

                return Matrix(HashMap(points))
            }
        }
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(direction: Direction): Point = Point(x + direction.modX, y + direction.modY)
    }

    enum class Direction(val modX: Int, val modY: Int) {
        NORTH(0, -1) {
            override fun opposite(): Direction = SOUTH
            override fun left90(): Direction = WEST
            override fun right90(): Direction = EAST
        },
        NORTHEAST(1, -1) {
            override fun opposite(): Direction = SOUTHWEST
            override fun left90(): Direction = NORTHWEST
            override fun right90(): Direction = SOUTHEAST
             },
        EAST(1, 0) {
            override fun opposite(): Direction = WEST
            override fun left90(): Direction = NORTH
            override fun right90(): Direction = SOUTH
       },
        SOUTHEAST(1, 1) {
            override fun opposite(): Direction = NORTHWEST
            override fun left90(): Direction = NORTHEAST
            override fun right90(): Direction = SOUTHWEST
            },
        SOUTH(0, 1) {
            override fun opposite(): Direction = NORTH
            override fun left90(): Direction = EAST
            override fun right90(): Direction = WEST
        },
        SOUTHWEST(-1, 1) {
            override fun opposite(): Direction = NORTHEAST
            override fun left90(): Direction = SOUTHEAST
            override fun right90(): Direction = NORTHWEST
             },
        WEST(-1, 0) {
            override fun opposite(): Direction = EAST
            override fun left90(): Direction = SOUTH
            override fun right90(): Direction = NORTH
        },
        NORTHWEST(-1, -1) {
            override fun opposite(): Direction = SOUTHEAST
            override fun left90(): Direction = SOUTHWEST
            override fun right90(): Direction = NORTHEAST
        };

        abstract fun opposite(): Direction
        abstract fun left90(): Direction
        abstract fun right90(): Direction
    }

    val CardinalDirections = listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
    val InterCardinalDirections = listOf(Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST)
}