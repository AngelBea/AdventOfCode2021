package classes

class HydrotermalVentsCourse(val vents: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>, range: Int) {
    private var ventBoard: Array<Array<Int>> = arrayOf()

    init {
        ventBoard = Array(range){Array(range){ 0 } }
    }

    fun traceLine(fromTo: Pair<Pair<Int, Int>, Pair<Int, Int>>, secondPart: Boolean = false){
        val fromX = fromTo.first.first
        val toX = fromTo.second.first

        val fromY = fromTo.first.second
        val toY = fromTo.second.second

        val rangeDeciderX = if (fromX > toX) fromX downTo toX else fromX..toX
        val rangeDeciderY = if (fromY > toY) fromY downTo toY else fromY..toY

        if (fromX != toX && fromY == toY){
            for (num in rangeDeciderX){
                ventBoard[fromTo.second.second][num] += 1
            }
        }else if (fromY != toY && fromX == toX){
            for (num in rangeDeciderY){
                ventBoard[num][fromTo.second.first] += 1
            }
        }else if (fromY != toY && fromX != toX && secondPart){
            for ((y,x) in rangeDeciderY.zip(rangeDeciderX)){
                ventBoard[y][x] += 1
            }
        }
    }

    fun printBoard(){
        ventBoard.forEach { it.joinToString("|").let(::println) }
    }

    fun countIntersections() = ventBoard.sumOf { it.count { num -> num >= 2 } }.toString()
}