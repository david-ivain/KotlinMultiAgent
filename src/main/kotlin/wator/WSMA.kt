package wator

import core.SMA
import core.Scheduling

class WSMA(pEnv: WEnvironment, pAgents: MutableList<WAgent<*>>) : SMA<WEnvironment, WAgent<*>, WData>(pEnv, pAgents) {
    override val data: WData get() = WData(currentTick, env.refreshInterval, fishPopulation, sharkPopulation)
    private val newborns: MutableList<WAgent<*>> = mutableListOf()
    val fishPopulation: Int get() = agents.count { it is Fish }
    val sharkPopulation: Int get() = agents.count { it is Shark }
    override fun playTurn() {
        if (env.scheduling == Scheduling.Random) agents.shuffle()
        agents.forEach { fish ->
            fish.decide(env, board)
            fish.baby?.also {
                newborns.add(it)
                fish.baby = null
            }
            fish.update(env, board)
        }
        agents.addAll(newborns)
        //agents.filter { it.dead }.forEach { board[it.position.x][it.position.y] = null }
        agents.removeIf { it.dead }
        newborns.clear()
    }
}