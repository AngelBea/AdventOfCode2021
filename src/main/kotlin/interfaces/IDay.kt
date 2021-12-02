package interfaces

import ReaderUtils

interface IDay {
    var className: String
        get() = javaClass.simpleName
        set(value) = TODO()
    var result: String
    var reader: ReaderUtils

    fun first(){}
    fun second(){}

    fun printResult(){
        "The $className ${Thread.currentThread().stackTrace[3].methodName} result is: $result".let(::println)
    }

    fun getResult(){
        first()
        second()
    }
}