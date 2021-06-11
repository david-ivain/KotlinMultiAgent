package particules

import core.*
import kotlin.random.Random

fun main() {
    val r = Random.Default

    val canvasSize = Vector2(800, 600)
    val gridSize = Vector2(500, 500)
    val nbParticles = 10000
    val torus = false
    val delay = 30
    val scheduling = Scheduling.Sequential
    val gridIsVisible = false
    val ticks = 0
    val refresh = 1

    val pEnv = PEnvironment(canvasSize, gridSize, torus, delay, scheduling, gridIsVisible, ticks, refresh)
    val particles: MutableList<Particle> = mutableListOf()
    for (i in 0 until nbParticles) {
        var particle = Particle(Vector2(r.nextInt(gridSize.x), r.nextInt(gridSize.y)), Vector2(r.nextInt(3) - 1, r.nextInt(3) - 1))
        particles.forEach {
            while (particle.position.x == it.position.x && particle.position.y == it.position.y || particle.step.x == 0 && particle.step.y == 0) {
                particle = Particle(Vector2(r.nextInt(gridSize.x), r.nextInt(gridSize.y)), Vector2(r.nextInt(3) - 1, r.nextInt(3) - 1))
            }
        }
        particles.add(particle)
    }
    val sma = PSMA(pEnv, particles)
    val view = PView(sma)
    view.pack()
    view.isVisible = true
    sma.listener = view
    sma.run()
}