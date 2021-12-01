package days

interface IDays {
    var result: String

    fun getResult(){}
    fun printResult(){
        "The result of ${javaClass.simpleName} is: $result".let(::println)
    }
}