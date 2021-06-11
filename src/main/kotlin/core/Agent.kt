package core

import java.awt.Color

abstract class Agent<E:Environment, A:Agent<E,A>>(var position: Point2, var step: Vector2) {
    abstract val color: Color
    abstract fun decide(env: E, board: List<MutableList<A?>>)
    open fun update(env: E, board: List<MutableList<A?>>) {
        if (board[position.x][position.y] == this) board[position.x][position.y] = null
        this.position = calculateNextPoint(env, step)
        @Suppress("UNCHECKED_CAST")
        board[position.x][position.y] = this as A
    }
    fun calculateNextPoint(env: Environment, direction: Vector2): Point2 {
        val gx = env.gridSize.x
        val gy = env.gridSize.y
        val torus = env.torus
        val newPosition = position.translate(direction)
        return if (torus) Point2(newPosition.x % gx, newPosition.y % gy)
        else newPosition
    }
}
