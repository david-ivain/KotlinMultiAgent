package wator

import core.Environment
import core.Scheduling
import core.Vector2

class WEnvironment(
    canvasSize: Vector2, gridSize: Vector2, torus: Boolean, delay: Int, scheduling: Scheduling,
    gridIsVisible: Boolean, ticks: Int, refreshInterval: Int,
    val fishBreedTime: Int,
    val sharkBreedTime: Int,
    val initialFishCount: Int,
    val initialSharkCount: Int,
    val fishLifeTime: Int,
    val sharkStarveTime: Int
) : Environment(
    canvasSize, gridSize, torus,
    delay,
    scheduling, gridIsVisible, ticks, refreshInterval
)