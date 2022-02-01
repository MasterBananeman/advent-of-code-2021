fun main() {
    val input = readInput("day1-inputA")
    val sweepData = input.map { it.toInt() }
    println("Answer 1 :${sweepData.countIncreases()}")
    val tripletData = mutableListOf<Int>()
    for (i in 0 until sweepData.size - 2){
        tripletData.add(sweepData.slice(i..i+2).sum())
    }
    println("Answer 2 :${tripletData.countIncreases()}")
}
fun List<Int>.countIncreases() : Int = filterIndexed { i, e -> i > 0 && e > this[i - 1]}.count()
