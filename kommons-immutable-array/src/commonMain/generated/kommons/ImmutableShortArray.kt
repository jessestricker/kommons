/*
 * The source code in this file is auto-generated, do not edit manually.
 * Generator: kommons.buildsrc.immutablearray.ImmutableArraysGenerator
 */

@file:Suppress("TooManyFunctions")

package kommons

import kommons.internal.requireIndex

/** An immutable array of shorts. */
public class ImmutableShortArray
@PublishedApi
internal constructor(
    internal val data: ShortArray,
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
    public operator fun get(index: Int): Short {
        requireIndex(index, size)
        return data[dataStart + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): ShortIterator {
        return object : ShortIterator() {
            private var dataIndex = dataStart

            override fun hasNext(): Boolean {
                return dataIndex < dataEnd
            }

            override fun nextShort(): Short {
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
        other as ImmutableShortArray

        if (data === other.data && dataStart == other.dataStart && dataEnd == other.dataEnd)
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
        return "ImmutableShortArray(size=$size)"
    }
}

/**
 * Creates an immutable array of shorts of the given [size], with every element initialized to `0`.
 */
public fun ImmutableShortArray(size: Int): ImmutableShortArray {
    return ImmutableShortArray(ShortArray(size))
}

/**
 * Creates an immutable array of shorts of the given [size], with every element initialized by the
 * given [init] function.
 */
public inline fun ImmutableShortArray(size: Int, init: (index: Int) -> Short): ImmutableShortArray {
    return ImmutableShortArray(ShortArray(size) { init(it) })
}

/** Creates a new immutable array of shorts which contains the given [elements]. */
public fun immutableShortArrayOf(vararg elements: Short): ImmutableShortArray {
    return ImmutableShortArray(elements)
}

/** Returns a new immutable array which contains the elements of this array. */
public fun ShortArray.toImmutableArray(): ImmutableShortArray {
    return ImmutableShortArray(this.copyOf())
}

/**
 * Returns a new immutable array which contains the elements of this array from given [startIndex]
 * (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableShortArray.size].
 */
public fun ShortArray.toImmutableArray(startIndex: Int, endIndex: Int): ImmutableShortArray {
    return ImmutableShortArray(this.copyOfRange(startIndex, endIndex))
}

/** Returns whether this array is empty. */
public fun ImmutableShortArray.isEmpty(): Boolean {
    return size == 0
}

/** Returns whether this array is not empty. */
public fun ImmutableShortArray.isNotEmpty(): Boolean {
    return !isEmpty()
}

/** The last valid index. */
public val ImmutableShortArray.lastIndex: Int
    get() = size - 1

/** The range of valid indices. */
public val ImmutableShortArray.indices: IntRange
    get() = IntRange(0, lastIndex)

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableShortArray.asList(): List<Short> {
    return object : AbstractList<Short>(), RandomAccess {
        override val size: Int
            get() = this@asList.size

        override fun contains(element: Short): Boolean = this@asList.contains(element)

        override fun get(index: Int): Short = this@asList[index]

        override fun indexOf(element: Short): Int = this@asList.indexOf(element)

        override fun isEmpty(): Boolean = this@asList.isEmpty()

        override fun iterator(): Iterator<Short> = this@asList.iterator()

        override fun lastIndexOf(element: Short): Int = this@asList.lastIndexOf(element)
    }
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableShortArray.contains(element: Short): Boolean {
    return indexOf(element) != -1
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableShortArray.indexOf(value: Short): Int {
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
public fun ImmutableShortArray.lastIndexOf(value: Short): Int {
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
 * than [endIndex], or [endIndex] is greater than [size][ImmutableShortArray.size].
 */
public fun ImmutableShortArray.sliceArray(startIndex: Int, endIndex: Int): ImmutableShortArray {
    // 0 <= startIndex <= endIndex <= size
    require(0 <= startIndex) { "startIndex $startIndex must be greater than or equal to 0" }
    require(startIndex <= endIndex) {
        "startIndex $startIndex must be less than or equal to endIndex $endIndex"
    }
    require(endIndex <= size) { "endIndex $endIndex must be less than or equal to size $size" }

    return ImmutableShortArray(data, dataStart + startIndex, dataStart + endIndex)
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableShortArray.toMutableArray(): ShortArray {
    return data.copyOfRange(dataStart, dataEnd)
}
