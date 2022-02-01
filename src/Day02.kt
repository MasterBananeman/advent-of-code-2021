fun main() {
    val input = readInput("day2-inputA")

    var horizontal = 0
    var depth = 0
    val pairedInput = input.map {
        val splitted = it.split(" ")
        Pair(splitted[0], splitted[1].toInt())
    }
    pairedInput.forEach {
        when(it.first) {
            "down" -> depth += it.second
            "up" -> depth -= it.second
            "forward" -> horizontal += it.second
        }
    }

    println("Answer 1 : ${horizontal * depth}")

    var horizontal2 = 0
    var depth2 = 0
    var aim = 0
    pairedInput.forEach {
        when(it.first) {
            "down" -> aim += it.second
            "up" -> aim -= it.second
            "forward" -> {
                horizontal2 += it.second
                depth2 += it.second * aim
            }
        }
    }

    println("Answer 2 : ${horizontal2 * depth2}")
}
