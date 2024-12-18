/*
 * The source code in this file is auto-generated, do not edit manually.
 * Generator: kommons.buildsrc.immutablearray.ImmutableArraysGenerator
 */

package kommons

import kommons.internal.requireIndex

/** An immutable array of longs. */
public class ImmutableLongArray
@PublishedApi
internal constructor(
    internal val data: LongArray,
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
    public operator fun get(index: Int): Long {
        requireIndex(index, size)
        return data[dataStart + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): LongIterator {
        return object : LongIterator() {
            private var dataIndex = dataStart

            override fun hasNext(): Boolean {
                return dataIndex < dataEnd
            }

            override fun nextLong(): Long {
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
        other as ImmutableLongArray

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
        return "ImmutableLongArray(size=$size)"
    }
}

/**
 * Creates an immutable array of longs of the given [size], with every element initialized to `0L`.
 */
public fun ImmutableLongArray(size: Int): ImmutableLongArray {
    return ImmutableLongArray(LongArray(size))
}

/**
 * Creates an immutable array of longs of the given [size], with every element initialized by the
 * given [init] function.
 */
public inline fun ImmutableLongArray(size: Int, init: (index: Int) -> Long): ImmutableLongArray {
    return ImmutableLongArray(LongArray(size) { init(it) })
}

/** Creates a new immutable array of longs which contains the given [elements]. */
public fun immutableLongArrayOf(vararg elements: Long): ImmutableLongArray {
    return ImmutableLongArray(elements)
}

/** Returns a new immutable array which contains the elements of this array. */
public fun LongArray.toImmutableArray(): ImmutableLongArray {
    return ImmutableLongArray(this.copyOf())
}

/**
 * Returns a new immutable array which contains the elements of this array from given [startIndex]
 * (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableLongArray.size].
 */
public fun LongArray.toImmutableArray(startIndex: Int, endIndex: Int): ImmutableLongArray {
    return ImmutableLongArray(this.copyOfRange(startIndex, endIndex))
}

/** Returns whether this array is empty. */
public fun ImmutableLongArray.isEmpty(): Boolean {
    return size == 0
}

/** Returns whether this array is not empty. */
public fun ImmutableLongArray.isNotEmpty(): Boolean {
    return !isEmpty()
}

/** The last valid index. */
public val ImmutableLongArray.lastIndex: Int
    get() = size - 1

/** The range of valid indices. */
public val ImmutableLongArray.indices: IntRange
    get() = IntRange(0, lastIndex)

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableLongArray.asList(): List<Long> {
    return object : AbstractList<Long>(), RandomAccess {
        override val size: Int
            get() = this@asList.size

        override fun contains(element: Long): Boolean = this@asList.contains(element)

        override fun get(index: Int): Long = this@asList[index]

        override fun indexOf(element: Long): Int = this@asList.indexOf(element)

        override fun isEmpty(): Boolean = this@asList.isEmpty()

        override fun iterator(): Iterator<Long> = this@asList.iterator()

        override fun lastIndexOf(element: Long): Int = this@asList.lastIndexOf(element)
    }
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableLongArray.contains(element: Long): Boolean {
    return indexOf(element) != -1
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableLongArray.indexOf(value: Long): Int {
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
public fun ImmutableLongArray.lastIndexOf(value: Long): Int {
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
 * than [endIndex], or [endIndex] is greater than [size][ImmutableLongArray.size].
 */
public fun ImmutableLongArray.sliceArray(startIndex: Int, endIndex: Int): ImmutableLongArray {
    // 0 <= startIndex <= endIndex <= size
    require(0 <= startIndex) { "startIndex $startIndex must be greater than or equal to 0" }
    require(startIndex <= endIndex) {
        "startIndex $startIndex must be less than or equal to endIndex $endIndex"
    }
    require(endIndex <= size) { "endIndex $endIndex must be less than or equal to size $size" }

    return ImmutableLongArray(data, dataStart + startIndex, dataStart + endIndex)
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableLongArray.toMutableArray(): LongArray {
    return data.copyOfRange(dataStart, dataEnd)
}
