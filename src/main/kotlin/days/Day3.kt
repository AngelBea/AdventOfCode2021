package days

import CHAR_ONE
import CHAR_ZERO
import INPUT_DAY_3
import ReaderUtils
import interfaces.IDay

class Day3 : IDay {

    override var result = ""

    override var reader = ReaderUtils(INPUT_DAY_3)

    override fun first() {
        val diagnosisLines = reader.getLines()
        var gammaRate = ""
        var epsilonRate = ""

        for (pos in 0 until diagnosisLines.first().length) {
            val countOne = diagnosisLines.count { it[pos] == '1' }
            val countZero = diagnosisLines.count { it[pos] == '0' }

            if (countOne > countZero) {
                gammaRate += '1'
                epsilonRate += '0'
            } else {
                gammaRate += '0'
                epsilonRate += '1'
            }
        }

        result = (gammaRate.toInt(2) * epsilonRate.toInt(2)).toString(10)

        printResult()
    }

    override fun second() {
        var diagnosisOxygenSupply = reader.getLines()
        var diagnosisCO2Supply = reader.getLines()

        val oxygenSupply = calculateOccurrencesOnSupply(diagnosisOxygenSupply, PrioritySupply.MOST, CHAR_ONE)
        val co2Supply = calculateOccurrencesOnSupply(diagnosisCO2Supply, PrioritySupply.LEAST, CHAR_ZERO)

        result = (oxygenSupply.toInt(2) * co2Supply.toInt(2)).toString(10)

        printResult()
    }

    private fun calculateOccurrencesOnSupply(supplyList: List<String>, prioritySupply: PrioritySupply, prioritizedByte: Char): String{
        var auxList = supplyList

        for (posSupplyList in 0 until auxList.first().length){
            val countOne = auxList.count { it[posSupplyList] == '1' }
            val countZero = auxList.count { it[posSupplyList] == '0' }

            val hasOneMoreOccurrences = countOne > countZero
            val hasZeroMoreOccurrences = countZero > countOne
            val isOnlyOneRemaining = auxList.size == 1
            auxList = when {
                isOnlyOneRemaining -> break
                hasOneMoreOccurrences -> {
                    val charToFilter = checkWhetherLeastOrMost(prioritySupply, CHAR_ONE, CHAR_ZERO)
                    auxList.filter { it[posSupplyList] == charToFilter }
                }
                hasZeroMoreOccurrences -> {
                    val charToFilter = checkWhetherLeastOrMost(prioritySupply, CHAR_ZERO, CHAR_ONE)
                    auxList.filter { it[posSupplyList] == charToFilter }
                }
                else -> {
                    auxList.filter { it[posSupplyList] == prioritizedByte }
                }
            }
        }

        return auxList.first()
    }

    private fun checkWhetherLeastOrMost(prioritySupply: PrioritySupply, mostChar: Char, leastChar: Char): Char{
        return when(prioritySupply){
            PrioritySupply.LEAST -> leastChar
            PrioritySupply.MOST -> mostChar
        }
    }
    enum class PrioritySupply{
        LEAST,
        MOST
    }
}