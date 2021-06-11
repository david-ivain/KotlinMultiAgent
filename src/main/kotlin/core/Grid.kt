package core

import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

abstract class Grid<E : Environment, A : Agent<E, A>>(private val env: E, private val backgroundColor: Color, private val agents: MutableList<A>): JPanel() {
    private var lastFrame: Long = System.currentTimeMillis()

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        val gridWidth = env.gridSize.x
        val gridHeight = env.gridSize.y
        val canvasWidth = width
        val canvasHeight = height
        val boxSize = Vector2d(canvasWidth.toDouble() / gridWidth, canvasHeight.toDouble() / gridHeight)
        g?.color = backgroundColor
        g?.fillRect(0, 0, canvasWidth, canvasHeight)
        if (env.gridIsVisible) {
            g?.color = Color.black
            for (x in 1 until gridWidth) {
                g?.drawLine((x * boxSize.x).toInt(), 0, (x * boxSize.x).toInt(), canvasHeight)
            }
            for (y in 1 until gridHeight) {
                g?.drawLine(0, (y * boxSize.y).toInt(), canvasWidth, (y * boxSize.y).toInt())
            }
        }
        agents.toList().forEach {
            g?.color = it.color
            g?.fillOval((it.position.x * boxSize.x).toInt(), (it.position.y * boxSize.y).toInt(),
                boxSize.x.toInt(), boxSize.y.toInt()
            )
        }
        val newFrame = System.currentTimeMillis()
        g?.color = Color.black
        if (newFrame - lastFrame > 0) g?.drawString((1 / ((newFrame - lastFrame) / 1000f)).toInt().toString(), 0, 50)
        else g?.drawString("unavailable", 0, 50)
        lastFrame = newFrame
    }
}