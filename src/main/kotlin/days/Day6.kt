package days

import INPUT_DAY_6
import ReaderUtils
import classes.LanternFish
import interfaces.IDay

class Day6: IDay {

    override var result: String = ""
    override var reader = ReaderUtils(INPUT_DAY_6)

    override fun first() {
        lanternFishCalculationOptimized(80)
    }

    override fun second() {
        lanternFishCalculationOptimized(256)
    }

    //Brute code for solution, not working on part 2.
    private fun lanternFishCalculation(days: Int){
        val lanternFishSchool = reader.getFullFile().split(",").map{ it.toInt() }.map { LanternFish(it, null) }

        for (day in 1..days){
            lanternFishSchool.forEach {
                var currentChildren: MutableList<LanternFish> = mutableListOf(it)
                while (currentChildren.isNotEmpty()){
                    currentChildren.forEach{ lantern ->
                        lantern.reduceCycle()
                    }

                    currentChildren = currentChildren.flatMap { lantern -> lantern.children }.toMutableList()
                }
            }
        }

        var totalAfterEightyDays = 0

        lanternFishSchool.forEach {
            var currentChildren: MutableList<LanternFish> = mutableListOf(it)
            while (currentChildren.isNotEmpty()){
                totalAfterEightyDays += currentChildren.sumOf { lantern -> lantern.children.count() }

                currentChildren = currentChildren.flatMap { lantern -> lantern.children }.toMutableList()
            }
        }

        result = totalAfterEightyDays.plus(lanternFishSchool.size).toString()
        printResult()
    }

    private fun lanternFishCalculationOptimized(days: Int){
        val lanternFishSchool = reader.getFullFile().split(",").map{ it.toInt() }
        val lanterns = Array(9){0.toLong()}

        lanternFishSchool.groupBy { it }.map { it.key to it.value.count().toLong() }.forEach {
            lanterns[it.first] += it.second
        }

        for (i in 1..days){
            val auxLantern = lanterns.clone()

            (8 downTo 0).forEach {
                if (it == 0){
                    lanterns[6] += auxLantern[0]
                    lanterns[8] = auxLantern[0]
                }else{
                    lanterns[it - 1] = auxLantern[it]
                    if (it == 8) lanterns[it] = 0
                }
            }
        }

        result = lanterns.map { it.toBigInteger() }.sumOf { it }.toString()

        printResult()
    }
}