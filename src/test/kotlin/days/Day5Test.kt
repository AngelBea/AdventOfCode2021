package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5Test{
    @Test
    fun getResult(){
        TestingInfo.isRunning = true
        Day5(10).getResult()
    }
}