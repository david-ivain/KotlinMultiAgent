package particules

import core.Agent
import core.Point2
import core.Vector2
import java.awt.Color

class Particle(position: Point2, step: Vector2) : Agent<PEnvironment, Particle>(position, step) {
    var collision: Boolean
    override val color: Color get() = if (collision) Color.red else Color.gray
    init {
        collision = false
    }
    override fun decide(env: PEnvironment, board: List<MutableList<Particle?>>) {
        var nextPoint = calculateNextPoint(env, step)
        val targetParticle = board.getOrNull(nextPoint.x)?.getOrNull(nextPoint.y)
        targetParticle?.let {
            targetParticle.step = step .also { step = targetParticle.step }
            collision = true
            it.collision = true
        }
        nextPoint = calculateNextPoint(env, step)
        if (nextPoint.x >= env.gridSize.x || nextPoint.x < 0) step = Vector2(-step.x, step.y).also { collision = true }
        if (nextPoint.y >= env.gridSize.y || nextPoint.y < 0) step = Vector2(step.x, -step.y).also { collision = true }
    }
}