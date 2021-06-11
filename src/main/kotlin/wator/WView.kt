package wator

import core.View
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel

class WView(sma: WSMA) : View<WSMA, WData>("Wator", sma) {
    private lateinit var tickLabel: JLabel
    private lateinit var fishLabel: JLabel
    private lateinit var sharkLabel: JLabel

    override fun createGui(sma: WSMA) {
        layout = BorderLayout()
        add(WGrid(sma.env, sma.agents), BorderLayout.CENTER)
        val panel = JPanel()
        panel.minimumSize = Dimension(200, 0)
        val gridLayout = GridLayout(3, 1)
        panel.layout = gridLayout
        val tickArea = JPanel()
        tickArea.border = BorderFactory.createTitledBorder("Tick")
        tickLabel = JLabel("0")
        tickArea.add(tickLabel)
        val fishArea = JPanel()
        fishArea.border = BorderFactory.createTitledBorder("Fish's")
        fishArea.layout = BoxLayout(fishArea, BoxLayout.Y_AXIS)
        fishArea.add(JLabel("Initial: ${sma.env.initialFishCount}"))
        fishLabel = JLabel("Population: ${sma.fishPopulation}")
        fishArea.add(fishLabel)
        val sharkArea = JPanel()
        sharkArea.border = BorderFactory.createTitledBorder("Sharks")
        sharkArea.layout = BoxLayout(sharkArea, BoxLayout.Y_AXIS)
        sharkArea.add(JLabel("Initial: ${sma.env.initialSharkCount}"))
        sharkLabel = JLabel("Population: ${sma.sharkPopulation}")
        sharkArea.add(sharkLabel)
        panel.add(tickArea)
        panel.add(fishArea)
        panel.add(sharkArea)
        add(panel, BorderLayout.LINE_END)
    }

    override fun onTickChanged(data: WData) {
        tickLabel.text = data.currentTick.toString()
        fishLabel.text = "Population: ${data.fishPopulation}"
        sharkLabel.text = "Population: ${data.sharkPopulation}"
        if (data.currentTick % data.refreshInterval == 0) repaint()
    }
}