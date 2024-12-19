/*
 * The source code in this file is auto-generated, do not edit manually.
 * Generator: kommons-immutable-array/generator/src/main/kotlin/Generator.kt
 */

@file:Suppress("detekt:MagicNumber", "detekt:ReturnCount", "detekt:TooManyFunctions")

package kommons

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
public fun ByteArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: ByteArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
public fun ShortArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: ShortArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
public fun IntArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: IntArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
public fun LongArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: LongArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
public fun FloatArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: FloatArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
public fun DoubleArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: DoubleArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
public fun BooleanArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: BooleanArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
public fun CharArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: CharArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
@ExperimentalUnsignedTypes
public fun UByteArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: UByteArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
@ExperimentalUnsignedTypes
public fun UShortArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: UShortArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
@ExperimentalUnsignedTypes
public fun UIntArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: UIntArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}

/**
 * Checks if the contents of this array from the given [startIndex] (inclusive) to the given
 * [endIndex] (exclusive) are equal to the contents of the given [other] array from the given
 * [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
 */
@ExperimentalUnsignedTypes
public fun ULongArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: ULongArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    // check for same arrays with equal ranges
    if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    // check for size mismatch
    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    // check elements
    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < endIndex) {
        if (this[index++] != this[otherIndex++]) {
            return false
        }
    }
    return true
}
