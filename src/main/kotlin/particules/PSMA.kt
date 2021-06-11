package particules

import core.SMA
import core.Scheduling

class PSMA(pEnv: PEnvironment, pAgents: MutableList<Particle>) : SMA<PEnvironment, Particle, PData>(pEnv, pAgents) {
    override val data: PData get() = PData(currentTick, env.refreshInterval)
    override fun playTurn() {
        agents.forEach { it.collision = false }
        if (env.scheduling == Scheduling.Random) agents.shuffle()
        agents.forEach {
            it.decide(env, board)
            it.update(env, board)
        }
    }
}