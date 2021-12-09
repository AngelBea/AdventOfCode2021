package days

import INPUT_DAY_5
import ReaderUtils
import classes.HydrotermalVentsCourse
import interfaces.IDay

class Day5(val range: Int): IDay {
    override var result: String = ""
    override var reader = ReaderUtils(INPUT_DAY_5)
    val splitCoordsToPairs = {coord: String -> coord.split(",").let { it[0].toInt() to it[1].toInt() } }

    override fun first() {
        doCourse(false)
    }

    override fun second() {
        doCourse(true)
    }

    private fun doCourse(secondPart: Boolean){
        val regexFindCoordinates = "\\d{1,4},\\d{1,4}".toRegex()
        val fromCoordToCoordPairs = reader.getLines().map { regexFindCoordinates.findAll(it).map { result -> result.value.trim() }.toList() }
            .map { coordPair -> coordPair[0].let(splitCoordsToPairs) to coordPair[1].let(splitCoordsToPairs) }

        val course = HydrotermalVentsCourse(fromCoordToCoordPairs, this.range)

        fromCoordToCoordPairs.forEach {
            course.traceLine(it, secondPart)
        }

        if (TestingInfo.isRunning) course.printBoard()

        result = course.countIntersections()
        printResult()
    }
}