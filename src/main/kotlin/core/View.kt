package core

import java.awt.Dimension
import javax.swing.JFrame

abstract class View<S:SMA<*,*,L>, L:ListenerData<*>>(name: String, sma: S) : JFrame(name), TickChangedListener<L> {
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        preferredSize = Dimension(sma.env.canvasSize.x, sma.env.canvasSize.y)
        @Suppress("LeakingThis")
        createGui(sma)
    }

    abstract fun createGui(sma: S)
}