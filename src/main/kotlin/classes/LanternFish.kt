package classes

import java.util.function.Consumer

class LanternFish(var cycle: Int, private val parent: LanternFish? = null): Iterator<LanternFish> {
    val children = mutableListOf<LanternFish>()

    fun reduceCycle(){
        if(--cycle < 0){
            cycle = 6
            children.add(LanternFish(9, this))
        }
    }

    override fun hasNext(): Boolean = parent != null

    override fun next(): LanternFish = parent!!

    fun hasMoreChildren(): Boolean = children.isNotEmpty()

    fun nextChildren(): MutableList<LanternFish> = children

    override fun toString(): String {
        return "cycle = $cycle children = $children"
    }
}