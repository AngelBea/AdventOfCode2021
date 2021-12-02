package days

import INPUT_DAY_2
import ReaderUtils
import interfaces.IDay
import tr

class Day2: IDay {
    override var result = ""
    override var reader = ReaderUtils(INPUT_DAY_2)

    override fun first() {
        val instructions = reader.getLines().map { it.split(" ") }.map { it.first() to it.component2().toInt() }
        var initialPair = 0 to 0

        instructions.forEach { instruction ->
            val instructionUnit = instruction.second
            val horizontalPos = initialPair.first
            val depth = initialPair.second

            initialPair = when(Directions.values().first { it.direction == instruction.first }){
                Directions.FORWARD -> horizontalPos + instruction.second to depth
                Directions.UP -> horizontalPos to depth - instructionUnit
                Directions.DOWN -> horizontalPos to depth + instructionUnit
            }
        }

        result = "${initialPair.first * initialPair.second}"

        printResult()
    }

    override fun second() {
        val instructions = reader.getLines().map { it.split(" ") }.map { it.first() to it.component2().toInt() }
        var trio = 0 to 0 tr 0

        instructions.forEach { instruction ->
            val instructionUnit = instruction.second

            val horizontalPos = trio.first
            val depth = trio.second
            val aim = trio.third

            trio = when(Directions.values().first { it.direction == instruction.first }){
                Directions.FORWARD -> {
                    horizontalPos + instructionUnit to
                            depth + (instructionUnit * aim) tr aim
                }
                Directions.UP -> horizontalPos to depth tr aim - instructionUnit
                Directions.DOWN -> horizontalPos to depth tr aim + instructionUnit
            }
        }

        result = "${trio.first * trio.second}"

        printResult()
    }

    enum class Directions(val direction: String){
        FORWARD("forward"),
        UP("up"),
        DOWN("down")
    }
}