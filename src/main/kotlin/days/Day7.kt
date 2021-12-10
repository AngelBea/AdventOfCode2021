package days

import INPUT_DAY_7
import ReaderUtils
import Trio
import interfaces.IDay
import tr
import kotlin.math.abs
import kotlin.math.roundToInt

class Day7: IDay {
    override var result = ""
    override var reader = ReaderUtils(INPUT_DAY_7)

    override fun first() {
        val crabPositions = reader.getFullFile().split(",").map { it.toInt() }

        val allPositions = crabPositions.toSet()
        val positionsCount = crabPositions.groupBy { it }.map { it.key to it.value.count() }

        val fuelNeeded = mutableListOf<Trio<Int, Int, Int>>()
        allPositions.forEach { position ->
            positionsCount.forEach { positionCount ->
                fuelNeeded.add(position to positionCount.first tr abs((position - positionCount.first) * positionCount.second))
            }
        }

        val sumAll = fuelNeeded.groupBy { it.first }.map { it.key to it.value.sumOf { trio -> trio.third } }.minByOrNull { it.second }
        result = "${sumAll}"

        printResult()
    }

    override fun second() {
        val crabPositions = reader.getFullFile().split(",").map { it.toInt() }

        val bestHorPos = (crabPositions.sum().toDouble() / crabPositions.size.toDouble()).roundToInt()
        val considerAdjacentPos = listOf(bestHorPos - 1, bestHorPos, bestHorPos + 1)

        val positionsCount = crabPositions.groupBy { it }.map { it.key to it.value.count() }

        val fuelNeeded = mutableListOf<Trio<Int, Int, Int>>()
        considerAdjacentPos.forEach { adyacentPos ->
            positionsCount.forEach { positionCount ->
                fuelNeeded.add(adyacentPos to positionCount.first tr calculateAdvanceOfCrab(adyacentPos, positionCount.first) * positionCount.second)
            }
        }

        val sumAll = fuelNeeded.groupBy { it.first }.map { it.key to it.value.sumOf { trio -> trio.third } }.minByOrNull { it.second }

        result = "${sumAll}"

        printResult()
    }

    private fun calculateAdvanceOfCrab(position: Int, crabPosition: Int): Int {
        val fuelExpense = abs(position - crabPosition)

        return (0..fuelExpense).sum()
    }
}