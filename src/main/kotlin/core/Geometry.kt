package core

data class Point2(val x: Int, val y: Int) {
    fun translate(vector: Vector2): Point2 {
        return this + vector
    }
    operator fun unaryMinus() = Point2(-x, -y)
    operator fun plus(other: Point2) = Point2(x + other.x, y + other.y)
    operator fun minus(other: Point2) = Point2(x - other.x, y + other.y)
    override fun equals(other: Any?): Boolean {
        if (other !is Point2) return false
        return this.x == other.x && this.y == other.y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}
typealias Vector2 = Point2

data class Point2d(val x: Double, val y: Double) {
    fun translate(vector: Vector2d): Point2d {
        return this + vector
    }
    fun translate(vector: Vector2): Point2d {
        return this + vector
    }
    fun toPoint2(): Point2 = Point2(x.toInt(), y.toInt())
    operator fun unaryMinus() = Point2d(-x, -y)
    operator fun plus(other: Point2d) = Point2d(x + other.x, y + other.y)
    operator fun plus(other: Point2) = Point2d(x + other.x, y + other.y)
    operator fun minus(other: Point2d) = Point2d(x - other.x, y + other.y)
    operator fun minus(other: Point2) = Point2d(x - other.x, y + other.y)
    override fun equals(other: Any?): Boolean {
        if (other is Point2) return this.x == other.x.toDouble() && this.y == other.y.toDouble()
        if (other !is Point2d) return false
        return this.x == other.x && this.y == other.y
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }
}
typealias Vector2d = Point2d