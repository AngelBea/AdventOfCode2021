package classes

import CHECKED
import MARK

class BingoCard(var lines: List<MutableList<String>>, val index: Int, var blocked: Boolean = false) {

    fun markCell(column: Int, row: Int) {
        this.lines[row][column] = MARK
    }
    
    fun getCellIndexOf(number: String): Pair<Int, Int>{
        val indexes = lines.mapIndexed { index, row -> index to row.indexOf(number)}


        return try{
            indexes.first { it.second > -1 }
        }catch (exc: NoSuchElementException){
            Pair(-1, -1)
        }

    }

    fun checkIfWin(): Boolean{
        val isAnyLineCompleted = lines.any { it.all { cell -> cell == MARK } }
        val isAnyColumnCompleted = (lines.indices).any { getColumn(it).all { cell -> cell == MARK } }

        return isAnyLineCompleted || isAnyColumnCompleted
    }

    fun checkAllMinus(){
        lines = lines.map { it.joinToString(" ").replace(MARK, CHECKED).split(" ").toMutableList() }
    }

    fun sumRemainingCells() = lines.sumOf { line -> line.filter { it != MARK && it != CHECKED }.sumOf { it.toInt() } }


    private fun getColumn(columnIndex: Int) = lines.mapIndexed { index, _ -> lines[index][columnIndex] }

}