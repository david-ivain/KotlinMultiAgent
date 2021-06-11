package core

import kotlin.properties.Delegates

abstract class SMA<E:Environment, A:Agent<E, A>, L:ListenerData<A>>(pEnv: E, pAgents: MutableList<A>) {
    var currentTick: Int by Delegates.observable(0) {_, _, _ -> listener?.onTickChanged(data) }
    val env: E = pEnv
    val agents: MutableList<A> = pAgents
    var listener: TickChangedListener<L>? = null
    protected val board: List<MutableList<A?>>
    abstract val data: L
    init {
        board = List(env.gridSize.x) { MutableList(env.gridSize.y){ null } }
        pAgents.forEach { board[it.position.x][it.position.y] = it }
    }
    private fun sleep() {
        Thread.sleep(env.delay.toLong())
    }
    fun run() {
        val ticks = env.ticks
        if (ticks == 0 ) {
            while (true) {
                playTurn()
                currentTick++
                sleep()
            }
        } else {
            while (currentTick < ticks) {
                playTurn()
                currentTick++
                sleep()
            }
        }
    }
    abstract fun playTurn()
}

