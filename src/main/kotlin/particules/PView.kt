package particules

import core.*
import java.awt.BorderLayout

class PView(sma: PSMA) : View<PSMA, PData>("Particules", sma) {
    override fun createGui(sma: PSMA) {
        layout = BorderLayout()
        add(PGrid(sma.env, sma.agents), BorderLayout.CENTER)
    }

    override fun onTickChanged(data: PData) {
        if (data.currentTick % data.refreshInterval == 0) repaint()
    }
}