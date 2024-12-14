package kommons.internal

@Suppress("ReturnCount")
internal inline fun checkEqualsInRange(
    range: IntRange,
    otherRange: IntRange,
    elementEquals: (index: Int, otherIndex: Int) -> Boolean,
): Boolean {
    val rangeSize = range.size
    val otherRangeSize = otherRange.size
    if (rangeSize != otherRangeSize) {
        return false
    }

    var index = range.first
    var otherIndex = otherRange.first
    while (index <= range.last) {
        if (!elementEquals(index, otherIndex)) {
            return false
        }
        index++
        otherIndex++
    }

    return true
}

private const val HASH_CODE_FACTOR = 31

internal inline fun buildHashCodeOfRange(
    range: IntRange,
    elementHashCode: (index: Int) -> Int,
): Int {
    var result = 1
    for (index in range) {
        result = HASH_CODE_FACTOR * result + elementHashCode(index)
    }
    return result
}
