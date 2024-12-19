/*
 * The source code in this file is auto-generated, do not edit manually.
 * Generator: kommons-immutable-array/generator/src/main/kotlin/Generator.kt
 */

@file:Suppress("detekt:MagicNumber", "detekt:ReturnCount", "detekt:TooManyFunctions")

package kommons

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
public fun ByteArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
public fun ShortArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
public fun IntArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
public fun LongArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
public fun FloatArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
public fun DoubleArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
public fun BooleanArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
public fun CharArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
@ExperimentalUnsignedTypes
public fun UByteArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
@ExperimentalUnsignedTypes
public fun UShortArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
@ExperimentalUnsignedTypes
public fun UIntArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}

/**
 * Returns a hash code based on the contents of this array from the given [startIndex] (inclusive)
 * to the given [endIndex] (exclusive).
 */
@ExperimentalUnsignedTypes
public fun ULongArray.contentHashCode(startIndex: Int, endIndex: Int): Int {
    var result = 1
    for (index in startIndex..<endIndex) {
        result = 31 * result + this[index].hashCode()
    }
    return result
}
