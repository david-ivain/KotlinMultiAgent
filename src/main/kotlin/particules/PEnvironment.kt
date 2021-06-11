package particules

import core.Environment
import core.Scheduling
import core.Vector2

class PEnvironment(
    canvasSize: Vector2,
    gridSize: Vector2,
    torus: Boolean,
    delay: Int,
    scheduling: Scheduling,
    gridIsVisible: Boolean,
    ticks: Int,
    refreshInterval: Int
) : Environment(canvasSize,
    gridSize,
    torus,
    delay,
    scheduling,
    gridIsVisible,
    ticks,
    refreshInterval
)