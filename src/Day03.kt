import kotlin.math.pow

fun main(){
    val input = readInput("day3-inputA")
    val bitsColumns = Array<MutableList<Boolean>>(input.first().length) { mutableListOf() }
    input.forEach {
        for (i in bitsColumns.indices){
            bitsColumns[i].add(it[i] == '1')
        }
    }

    val binaryGammaRate = bitsColumns.map { allBits -> 1.takeIf { allBits.count { it } > allBits.size/2} ?: 0 }
    val binaryEpsilonRate = bitsColumns.map { allBits -> 1.takeIf { allBits.count { it } < allBits.size/2} ?: 0 }
    val gammaRate = convertToDecimal(binaryGammaRate)
    val epsilonRate = convertToDecimal(binaryEpsilonRate)
    println("Answer 1 :${gammaRate * epsilonRate}")

    var shrinkableInput = input.toMutableList()
    var referenceBits = bitsColumns.toMutableList()
    var chain = ""
    for (i in bitsColumns.indices){
        chain += referenceBits[i].run {
            val ones = this.count{ it }
            val isEven = this.size.mod(2) == 0
            val halfSize = this.size / 2
            when {
                ones > halfSize -> "1"
                ones == halfSize -> if(isEven) "1" else "0"
                else -> "0"
            }
        }

        shrinkableInput.removeAll { !it.startsWith(chain) }
        referenceBits.forEach { it.clear() }
        shrinkableInput.forEach {
            for (l in bitsColumns.indices){
                referenceBits[l].add(it[l] == '1')
            }
        }
    }
    val oxygenGeneratorRating = convertToDecimal(chain.map { it.toString().toInt() })

    // reset
    shrinkableInput = input.toMutableList()
    referenceBits = bitsColumns.toMutableList()
    chain = ""

    for (i in bitsColumns.indices){
        chain += referenceBits[i].run {
            val ones = this.count{ it }
            val isEven = this.size.mod(2) == 0
            val halfSize = this.size / 2
            when {
                ones < halfSize -> "1"
                ones == halfSize -> if(isEven) "0" else "1"
                else -> "0"
            }
        }

        shrinkableInput.removeAll { !it.startsWith(chain) }
        if (shrinkableInput.size == 1) {
            chain = shrinkableInput.first()
            break
        }
        else {
            referenceBits.forEach { it.clear() }
            shrinkableInput.forEach {
                for (l in bitsColumns.indices){
                    referenceBits[l].add(it[l] == '1')
                }
            }
        }
    }
    val CO2ScrubberRating = convertToDecimal(chain.map { it.toString().toInt() })
    println("Answer 2 : ${oxygenGeneratorRating * CO2ScrubberRating}")
}

fun convertToDecimal(binaryList : List<Int>) : Int {
    val reversed = binaryList.reversed()
    var count = 0
    for(i in binaryList.size -1 downTo 0) {
        if(reversed[i] == 1) count += 2.toDouble().pow(i).toInt()
    }
    return count
}