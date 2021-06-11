package wator

import core.Point2
import core.Vector2
import java.awt.Color

class Fish(position: Point2, step: Vector2) : WAgent<Fish>(position, step) {
    override val color: Color get() = if (age == 0) Color.yellow else Color.green

    override fun decide(env: WEnvironment, board: List<MutableList<WAgent<*>?>>) {
        if (dead) return
        val (empties, _) = emptySquaresAndFishs(env, board)
        if (age >= env.fishLifeTime) {
            die()
            return
        }
        if (empties.isEmpty()) {
            step = Vector2(0, 0)
        } else {
            step = empties.random()
            if (cBreed >= env.fishBreedTime) {
                baby = breed()
                board[position.x][position.y] = baby
                cBreed = 0
            }
        }
        cBreed++
        age++
    }

    override fun breed(): Fish {
        return Fish(position, Vector2(0, 0))
    }
}