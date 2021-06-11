package particules

import core.ListenerData

class PData(currentTick: Int, refreshInterval: Int) : ListenerData<Particle>(currentTick,
    refreshInterval
)