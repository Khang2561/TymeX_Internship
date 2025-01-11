// Find by sum
fun findMissingNumberBySum(arr: IntArray): Int {
    val n = arr.size
    val arrSum = arr.sum()
    val totalSum = (n + 1) * (n + 2) / 2
    return totalSum - arrSum
}

// Find by sorting
fun findMissingNumberBySorting(arr: IntArray): Int {
    val n = arr.size + 1
    arr.sort()
    // Traverse the array to find the missing number
    for (i in arr.indices) {
        if (arr[i] != i + 1) {
            return i + 1
        }
    }
    // if the missing number is not found in the array, the missing number is n
    return n
}

fun main() {
    val arr = intArrayOf(3, 7, 1, 2, 6, 5)

    // 1. Find missing number by sum
    val findMissingNumberBySum = findMissingNumberBySum(arr)
    println("Missing number (by sum): $findMissingNumberBySum")

    // 2. Find missing number by sorting
    val findMissingNumberBySorting = findMissingNumberBySorting(arr)
    println("Missing number (by sorting): $findMissingNumberBySorting")
}
