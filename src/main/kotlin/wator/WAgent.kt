package wator

import core.Agent
import core.Point2
import core.Vector2

abstract class WAgent<T:WAgent<T>>(position: Point2, step: Vector2) : Agent<WEnvironment, WAgent<*>>(position, step) {
    var age: Int = 0
    var dead: Boolean = false
    var cBreed: Int = 0
    var baby: T? = null
    fun die() {
        step = Vector2(0, 0)
        dead = true
    }
    fun emptySquaresAndFishs(env: WEnvironment, board: List<MutableList<WAgent<*>?>>): Pair<List<Vector2>, List<Pair<Fish, Vector2>>> {
        val empties: MutableList<Vector2> = mutableListOf()
        val fishs: MutableList<Pair<Fish, Vector2>> = mutableListOf()
        for (x in -1 .. 1) {
            for (y in -1 .. 1) {
                val next = calculateNextPoint(env, Vector2(x, y))
                // println(next)
                board.getOrNull(next.x)?.getOrNull(next.y)?.let { if (it is Fish) fishs.add(Pair(it, Vector2(x, y))) } ?: run {
                    if (next.x in 0 until env.gridSize.x && next.y in 0 until env.gridSize.y) {
                        empties.add(Vector2(x, y))
                    }
                }
            }
        }
        /*
        println("position $position")
        println("step: $step")
        println(empties.joinToString(",", "{", "}"))
         */
        return Pair(empties, fishs)
    }

    abstract fun breed(): T

    override fun update(env: WEnvironment, board: List<MutableList<WAgent<*>?>>) {
        super.update(env, board)
        if (dead) board[position.x][position.y] = null
    }
}