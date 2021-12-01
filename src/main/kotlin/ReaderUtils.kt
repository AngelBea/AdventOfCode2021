import java.io.File

class ReaderUtils(uri: String) {
    private val file: File by lazy {
        File(javaClass.getResource(INPUT_DAY_1_1)!!.toURI())
    }

    fun getNumberLines(): List<Int> = file.readLines().map{ it.toInt() }
    fun getLines(): List<String> = file.readLines()
}