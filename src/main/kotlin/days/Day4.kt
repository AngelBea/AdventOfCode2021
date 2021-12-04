package days

import INPUT_DAY_4
import ReaderUtils
import classes.BingoCard
import interfaces.IDay
import tr

class Day4 : IDay {
    override var result: String = ""
    override var reader = ReaderUtils(INPUT_DAY_4)

    override fun first() {
        val lines = reader.getLines().toMutableList()
        lines.removeIf { it.isBlank() }

        val numbers = lines.first().split(",")
        lines.removeFirst()

        val bingos = lines.map { it.split("(?<=\\d) +".toRegex()).map { s -> s.trim() }.toMutableList() }.windowed(5, 5).mapIndexed { index, list ->  BingoCard(list, index) }

        numbers.forEach { number ->
            val bingosToModify = bingos.map { bingoCard -> bingoCard.getCellIndexOf(number) tr bingoCard.index}.filter { it.second > -1 }
            bingosToModify.forEach {
                bingos[it.third].markCell(it.second, it.first)
            }

            val winner = bingos.find { it.checkIfWin() }

            if (winner != null) {
                result = (winner.sumRemainingCells() * number.toInt()).toString()
                printResult()
                return
            }
        }
    }

    override fun second() {
        val lines = reader.getLines().toMutableList()
        lines.removeIf { it.isBlank() }

        val numbers = lines.first().split(",")
        lines.removeFirst()

        val bingos = lines.map { it.split("(?<=\\d) +".toRegex()).map { s -> s.trim() }.toMutableList() }.windowed(5, 5).mapIndexed { index, list ->  BingoCard(list, index) }.toMutableList()
        val winners = mutableListOf<Pair<BingoCard, String>>()

        numbers.forEachIndexed { index, number ->
            val bingosToModify = bingos.map { bingoCard -> bingoCard.getCellIndexOf(number) tr bingoCard.index}.filter { it.second > -1 }
            bingosToModify.forEach {
                if (!bingos[it.third].blocked) bingos[it.third].markCell(it.second, it.first)
            }

            val winnerFilter = bingos.filter { it.checkIfWin() && !it.blocked }

            winnerFilter.forEach {
                winners.add(it to number)
                bingos[it.index].blocked = true
                bingos[it.index].checkAllMinus()
            }
        }

        result = (winners.last().first.sumRemainingCells() * winners.last().second.toInt()).toString()

        printResult()
    }
}