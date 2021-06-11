package wator

import core.Scheduling
import core.Vector2
import javax.swing.UIManager
import kotlin.random.Random

fun main() {
    val r = Random.Default

    val canvasSize = Vector2(800, 600)
    val gridSize = Vector2(100, 100)
    val torus = false
    val delay = 15
    val scheduling = Scheduling.Sequential
    val gridIsVisible = false
    val ticks = 0
    val refresh = 1
    val initialSharkCount = 80
    val initialFishCount = 240
    val fishBreedTime = 20
    val sharkBreeTime = 20
    val fishLifetime = 50
    val sharkStaveTime = 20

    val pEnv = WEnvironment(canvasSize, gridSize, torus, delay, scheduling, gridIsVisible, ticks, refresh, fishBreedTime, sharkBreeTime, initialFishCount, initialSharkCount, fishLifetime, sharkStaveTime)
    val agents: MutableList<WAgent<*>> = mutableListOf()
    var taken: Boolean
    for (i in 0 until initialFishCount) {
        var fish: Fish
        do {
            fish = Fish(Vector2(r.nextInt(gridSize.x), r.nextInt(gridSize.y)), Vector2(0, 0))
            taken = agents.any { it.position == fish.position }
        } while (taken)
        agents.add(fish)
    }
    for (i in 0 until initialSharkCount) {
        var shark: Shark
        do {
            shark = Shark(Vector2(r.nextInt(gridSize.x), r.nextInt(gridSize.y)), Vector2(0, 0))
            taken = agents.any { it.position == shark.position }
        } while (taken)
        agents.add(shark)
    }
    val sma = WSMA(pEnv, agents)
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    } catch (e: Exception) {
        e.printStackTrace()
    }
    val view = WView(sma)
    view.pack()
    view.isVisible = true
    sma.listener = view
    sma.run()
}