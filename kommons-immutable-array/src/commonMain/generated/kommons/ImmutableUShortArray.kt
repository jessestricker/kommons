/*
 * The source code in this file is auto-generated, do not edit manually.
 * Generator: kommons.buildsrc.immutablearray.ImmutableArraysGenerator
 */

@file:Suppress("TooManyFunctions")

package kommons

import kommons.internal.requireIndex

/** An immutable array of unsigned shorts. */
@ExperimentalUnsignedTypes
public class ImmutableUShortArray
@PublishedApi
internal constructor(
    internal val data: UShortArray,
    internal val dataStart: Int = 0,
    internal val dataEnd: Int = data.size,
) {
    // 0 <= dataStart <= dataEnd <= data.size

    /** The number of elements. */
    public val size: Int
        get() = dataEnd - dataStart

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): UShort {
        requireIndex(index, size)
        return data[dataStart + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): Iterator<UShort> {
        return object : Iterator<UShort> {
            private var dataIndex = dataStart

            override fun hasNext(): Boolean {
                return dataIndex < dataEnd
            }

            override fun next(): UShort {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }
                return data[dataIndex++]
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as ImmutableUShortArray

        if (data == other.data && dataStart == other.dataStart && dataEnd == other.dataEnd)
            return true
        if (size != other.size) return false

        var dataIndex = dataStart
        var otherDataIndex = other.dataStart
        while (dataIndex < dataEnd) {
            if (data[dataIndex++] != other.data[otherDataIndex++]) {
                return false
            }
        }
        return true
    }

    override fun hashCode(): Int {
        var result = 1
        for (dataIndex in dataStart..<dataEnd) {
            result = 31 * result + data[dataIndex].hashCode()
        }
        return result
    }

    override fun toString(): String {
        return "ImmutableUShortArray(size=$size)"
    }
}

/**
 * Creates an immutable array of unsigned shorts of the given [size], with every element initialized
 * to `0u`.
 */
@ExperimentalUnsignedTypes
public fun ImmutableUShortArray(size: Int): ImmutableUShortArray {
    return ImmutableUShortArray(UShortArray(size))
}

/**
 * Creates an immutable array of unsigned shorts of the given [size], with every element initialized
 * by the given [init] function.
 */
@ExperimentalUnsignedTypes
public inline fun ImmutableUShortArray(
    size: Int,
    init: (index: Int) -> UShort,
): ImmutableUShortArray {
    return ImmutableUShortArray(UShortArray(size) { init(it) })
}

/** Creates a new immutable array of unsigned shorts which contains the given [elements]. */
@ExperimentalUnsignedTypes
public fun immutableUShortArrayOf(vararg elements: UShort): ImmutableUShortArray {
    return ImmutableUShortArray(elements)
}

/** Returns a new immutable array which contains the elements of this array. */
@ExperimentalUnsignedTypes
public fun UShortArray.toImmutableArray(): ImmutableUShortArray {
    return ImmutableUShortArray(this.copyOf())
}

/**
 * Returns a new immutable array which contains the elements of this array from given [startIndex]
 * (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableUShortArray.size].
 */
@ExperimentalUnsignedTypes
public fun UShortArray.toImmutableArray(startIndex: Int, endIndex: Int): ImmutableUShortArray {
    return ImmutableUShortArray(this.copyOfRange(startIndex, endIndex))
}

/** Returns whether this array is empty. */
@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.isEmpty(): Boolean {
    return size == 0
}

/** Returns whether this array is not empty. */
@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.isNotEmpty(): Boolean {
    return !isEmpty()
}

/** The last valid index. */
@ExperimentalUnsignedTypes
public val ImmutableUShortArray.lastIndex: Int
    get() = size - 1

/** The range of valid indices. */
@ExperimentalUnsignedTypes
public val ImmutableUShortArray.indices: IntRange
    get() = IntRange(0, lastIndex)

/** Returns an immutable [List] which contains the elements of this array. */
@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.asList(): List<UShort> {
    return object : AbstractList<UShort>(), RandomAccess {
        override val size: Int
            get() = this@asList.size

        override fun contains(element: UShort): Boolean = this@asList.contains(element)

        override fun get(index: Int): UShort = this@asList[index]

        override fun indexOf(element: UShort): Int = this@asList.indexOf(element)

        override fun isEmpty(): Boolean = this@asList.isEmpty()

        override fun iterator(): Iterator<UShort> = this@asList.iterator()

        override fun lastIndexOf(element: UShort): Int = this@asList.lastIndexOf(element)
    }
}

/** Returns whether this array contains the given [element]. */
@ExperimentalUnsignedTypes
public operator fun ImmutableUShortArray.contains(element: UShort): Boolean {
    return indexOf(element) != -1
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.indexOf(value: UShort): Int {
    for (dataIndex in dataStart..<dataEnd) {
        if (value == data[dataIndex]) {
            return dataIndex - dataStart
        }
    }
    return -1
}

/**
 * Returns the index of the last occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.lastIndexOf(value: UShort): Int {
    for (dataIndex in (dataStart..<dataEnd).reversed()) {
        if (value == data[dataIndex]) {
            return dataIndex - dataStart
        }
    }
    return -1
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableUShortArray.size].
 */
@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.sliceArray(startIndex: Int, endIndex: Int): ImmutableUShortArray {
    // 0 <= startIndex <= endIndex <= size
    require(0 <= startIndex) { "startIndex $startIndex must be greater than or equal to 0" }
    require(startIndex <= endIndex) {
        "startIndex $startIndex must be less than or equal to endIndex $endIndex"
    }
    require(endIndex <= size) { "endIndex $endIndex must be less than or equal to size $size" }

    return ImmutableUShortArray(data, dataStart + startIndex, dataStart + endIndex)
}

/** Returns a new mutable array which contains the elements of this array. */
@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.toMutableArray(): UShortArray {
    return data.copyOfRange(dataStart, dataEnd)
}
