package wator

import core.ListenerData

class WData(currentTick: Int, refreshInterval: Int, val fishPopulation: Int, val sharkPopulation: Int) : ListenerData<WAgent<*>>(currentTick, refreshInterval)