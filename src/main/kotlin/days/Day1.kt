package days

import INPUT_DAY_1
import ReaderUtils
import interfaces.IDay

class Day1 : IDay {
    override var result: String = ""
    override var reader = ReaderUtils(INPUT_DAY_1)

    override fun first() {
        val lines = reader.getNumberLines()
        result = lines.zipWithNext().count { it.first < it.second }.toString()
        printResult()
    }

    override fun second() {
        val lines = reader.getNumberLines()
        result = lines.windowed(3, 1)
            .map { it.sum() }
            .zipWithNext()
            .count{ it.first < it.second }.toString()

        printResult()
    }
}