package particules

import core.Grid
import java.awt.Color

class PGrid(env: PEnvironment, agents: MutableList<Particle>) : Grid<PEnvironment, Particle>(env, Color.white, agents)