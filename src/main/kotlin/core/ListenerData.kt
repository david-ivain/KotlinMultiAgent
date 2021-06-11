package core

abstract class ListenerData<A:Agent<*,A>>(val currentTick: Int, val refreshInterval: Int)