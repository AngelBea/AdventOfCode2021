data class Trio<T, T1, T2>(val first: T, val second: T1, val third: T2)
fun <T> List<T>.zipWithNextTwo(): List<Trio<T, T, T>> {
    val trios = this.mapIndexed { index, i ->
        if (index.plus(2) < this.size) return@mapIndexed Trio(i, this[index + 1], this[index + 2]) else null
    }.filterNotNull()

    return trios.toList()
}