package kommons

@Suppress("ReturnCount")
public fun BooleanArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: BooleanArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    if (this === other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < size) {
        if (this[index++] != other[otherIndex++]) {
            return false
        }
    }

    return true
}

@Suppress("ReturnCount")
public fun ByteArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: ByteArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    if (this === other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < size) {
        if (this[index++] != other[otherIndex++]) {
            return false
        }
    }

    return true
}

@Suppress("ReturnCount")
public fun CharArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: CharArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    if (this === other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < size) {
        if (this[index++] != other[otherIndex++]) {
            return false
        }
    }

    return true
}

@Suppress("ReturnCount")
public fun DoubleArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: DoubleArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    if (this === other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < size) {
        if (this[index++] != other[otherIndex++]) {
            return false
        }
    }

    return true
}

@Suppress("ReturnCount")
public fun FloatArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: FloatArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    if (this === other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < size) {
        if (this[index++] != other[otherIndex++]) {
            return false
        }
    }

    return true
}

@Suppress("ReturnCount")
public fun IntArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: IntArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    if (this === other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < size) {
        if (this[index++] != other[otherIndex++]) {
            return false
        }
    }

    return true
}

@Suppress("ReturnCount")
public fun LongArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: LongArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    if (this === other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < size) {
        if (this[index++] != other[otherIndex++]) {
            return false
        }
    }

    return true
}

@Suppress("ReturnCount")
public fun ShortArray.contentEquals(
    startIndex: Int,
    endIndex: Int,
    other: ShortArray,
    otherStartIndex: Int,
    otherEndIndex: Int,
): Boolean {
    if (this === other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
        return true
    }

    val size = endIndex - startIndex
    val otherSize = otherEndIndex - otherStartIndex
    if (size != otherSize) {
        return false
    }

    var index = startIndex
    var otherIndex = otherStartIndex
    while (index < size) {
        if (this[index++] != other[otherIndex++]) {
            return false
        }
    }

    return true
}
