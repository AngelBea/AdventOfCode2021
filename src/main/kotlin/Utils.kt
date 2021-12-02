data class Trio<A, B, C>(val first: A, val second: B, val third: C)

infix fun <T, T2, T3> Pair<T, T2>.tr(third: T3): Trio<T, T2, T3> = Trio(this.first, this.second, third)