package days

import INPUT_DAY_1_1
import ReaderUtils
import zipWithNextTwo

class Day1 : IDays {
    override var result: String = ""

    override fun getResult() {
        val reader = ReaderUtils(INPUT_DAY_1_1)
        val lines = reader.getNumberLines()
        result = lines.zipWithNext().count { it.first < it.second }.toString()
        printResult()

        result = lines.zipWithNextTwo()
            .map { it.first + it.second + it.third }
            .zipWithNext()
            .count{ it.first < it.second }.toString()

        printResult()
    }
}