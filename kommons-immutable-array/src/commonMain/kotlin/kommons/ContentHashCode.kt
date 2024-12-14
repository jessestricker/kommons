package kommons

private const val FACTOR = 31

public fun BooleanArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = FACTOR * result + this[index].hashCode()
    }
    return result
}

public fun ByteArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = FACTOR * result + this[index].hashCode()
    }
    return result
}

public fun CharArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = FACTOR * result + this[index].hashCode()
    }
    return result
}

public fun DoubleArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = FACTOR * result + this[index].hashCode()
    }
    return result
}

public fun FloatArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = FACTOR * result + this[index].hashCode()
    }
    return result
}

public fun IntArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = FACTOR * result + this[index].hashCode()
    }
    return result
}

public fun LongArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = FACTOR * result + this[index].hashCode()
    }
    return result
}

public fun ShortArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = FACTOR * result + this[index].hashCode()
    }
    return result
}
