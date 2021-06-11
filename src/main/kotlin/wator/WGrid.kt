package wator

import core.Grid
import java.awt.Color

class WGrid(env: WEnvironment, agents: MutableList<WAgent<*>>) : Grid<WEnvironment, WAgent<*>>(env, Color.cyan, agents)