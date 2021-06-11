package wator

import core.Point2
import core.Vector2
import java.awt.Color

class Shark(position: Point2, step: Vector2) : WAgent<Shark>(position, step) {
    override val color: Color get() = if (age == 0) Color.pink else Color.red
    private var cStarve: Int = 0
    override fun decide(env: WEnvironment, board: List<MutableList<WAgent<*>?>>) {
        var hasMoved = false
        val (empties, fishs) = emptySquaresAndFishs(env, board)
        if (cStarve >= env.sharkStarveTime) {
            die()
            return
        }
        when {
            fishs.isNotEmpty() -> {
                fishs.random().let {
                    hasMoved = true
                    cStarve = 0
                    it.first.die()
                    step = it.second
                }
            }
            empties.isNotEmpty() -> {
                empties.random().let {
                    hasMoved = true
                    step = it
                }
            }
            else -> step = Vector2(0, 0)
        }
        if (cBreed >= env.sharkBreedTime && hasMoved) {
            baby = breed()
            board[position.x][position.y] = baby
            cBreed = 0
        }
        cBreed++
        cStarve++
        age++
    }

    override fun breed(): Shark {
        return Shark(position, Vector2(0, 0))
    }
}