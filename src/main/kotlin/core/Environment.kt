package core

abstract class Environment(
    val canvasSize: Vector2,
    val gridSize: Vector2,
    val torus: Boolean,
    val delay: Int,
    val scheduling: Scheduling,
    val gridIsVisible: Boolean,
    val ticks: Int,
    val refreshInterval: Int
)
