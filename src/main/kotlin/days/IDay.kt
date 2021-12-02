package days

import ReaderUtils

interface IDay {
    var result: String
    var reader: ReaderUtils

    fun first(){}
    fun second(){}

    fun printResult(){
        "The ${Thread.currentThread().stackTrace[3].methodName} result is: $result".let(::println)
    }

    fun getResult(){
        first()
        second()
    }
}