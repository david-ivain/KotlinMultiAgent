package core

interface TickChangedListener<L:ListenerData<*>> {
    fun onTickChanged(data: L)
}