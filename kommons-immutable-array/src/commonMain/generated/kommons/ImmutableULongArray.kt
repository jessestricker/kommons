/*
 * The source code in this file is auto-generated, do not edit manually.
 * Generator: kommons-immutable-array/generator/src/main/kotlin/Generator.kt
 */

@file:Suppress("detekt:MagicNumber", "detekt:ReturnCount", "detekt:TooManyFunctions")

package kommons

import kommons.internal.requireIndex

/** An immutable array of unsigned longs. */
@ExperimentalUnsignedTypes
public class ImmutableULongArray
@PublishedApi
internal constructor(
    internal val data: ULongArray,
    internal val dataStart: Int = 0,
    internal val dataEnd: Int = data.size,
) {
    // 0 <= dataStart <= dataEnd <= data.size

    /**
     * Creates an immutable array of unsigned longs of the given [size], with every element
     * initialized to zero.
     */
    public constructor(size: Int) : this(ULongArray(size))

    /** The number of elements. */
    public val size: Int
        get() = dataEnd - dataStart

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): ULong {
        requireIndex(index in 0..<size) { "index $index must be within range 0..<$size" }
        return data[dataStart + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): Iterator<ULong> {
        return object : Iterator<ULong> {
            private var dataIndex = dataStart

            override fun hasNext(): Boolean {
                return dataIndex < dataEnd
            }

            override fun next(): ULong {
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
        other as ImmutableULongArray
        return data.contentEquals(dataStart, dataEnd, other.data, other.dataStart, other.dataEnd)
    }

    override fun hashCode(): Int {
        return data.contentHashCode(dataStart, dataEnd)
    }

    override fun toString(): String {
        return "ImmutableULongArray(size=$size)"
    }
}

/**
 * Creates an immutable array of unsigned longs of the given [size], with every element initialized
 * by the given [init] function.
 */
@ExperimentalUnsignedTypes
public inline fun ImmutableULongArray(size: Int, init: (index: Int) -> ULong): ImmutableULongArray {
    return ImmutableULongArray(ULongArray(size) { init(it) })
}

/** Creates an immutable array of unsigned longs which contains the given [elements]. */
@ExperimentalUnsignedTypes
public fun immutableULongArrayOf(vararg elements: ULong): ImmutableULongArray {
    return ImmutableULongArray(elements)
}

/** Returns a new immutable array which contains the elements of this array. */
@ExperimentalUnsignedTypes
public fun ULongArray.toImmutableArray(): ImmutableULongArray {
    return ImmutableULongArray(this.copyOf())
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableULongArray.size].
 */
@ExperimentalUnsignedTypes
public fun ULongArray.toImmutableArray(startIndex: Int, endIndex: Int): ImmutableULongArray {
    return ImmutableULongArray(this.copyOfRange(startIndex, endIndex))
}

/** Returns a new mutable array which contains the elements of this array. */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.toMutableArray(): ULongArray {
    return data.copyOfRange(dataStart, dataEnd)
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableULongArray.size].
 */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.sliceArray(startIndex: Int, endIndex: Int): ImmutableULongArray {
    require(0 <= startIndex) { "startIndex $startIndex must be greater than or equal to 0" }
    require(startIndex <= endIndex) {
        "startIndex $startIndex must be less than or equal to endIndex $endIndex"
    }
    require(endIndex <= size) { "endIndex $endIndex must be less than or equal to size $size" }
    return ImmutableULongArray(data, dataStart + startIndex, dataStart + endIndex)
}

/** The last valid index. */
@ExperimentalUnsignedTypes
public val ImmutableULongArray.lastIndex: Int
    get() = size - 1

/** The range of valid indices. */
@ExperimentalUnsignedTypes
public val ImmutableULongArray.indices: IntRange
    get() = 0..<size

/** Returns whether this array is empty. */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.isEmpty(): Boolean {
    return size == 0
}

/** Returns whether this array is not empty. */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.isNotEmpty(): Boolean {
    return size != 0
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.indexOf(value: ULong): Int {
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
public fun ImmutableULongArray.lastIndexOf(value: ULong): Int {
    for (dataIndex in (dataStart..<dataEnd).reversed()) {
        if (value == data[dataIndex]) {
            return dataIndex - dataStart
        }
    }
    return -1
}

/** Returns whether this array contains the given [element]. */
@ExperimentalUnsignedTypes
public operator fun ImmutableULongArray.contains(element: ULong): Boolean {
    return indexOf(element) != -1
}

/** Returns an immutable [List] which contains the elements of this array. */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.asList(): List<ULong> {
    return object : AbstractList<ULong>(), RandomAccess {
        override val size: Int
            get() = this@asList.size

        override fun contains(element: ULong): Boolean = this@asList.contains(element)

        override fun get(index: Int): ULong = this@asList[index]

        override fun indexOf(element: ULong): Int = this@asList.indexOf(element)

        override fun isEmpty(): Boolean = this@asList.isEmpty()

        override fun iterator(): Iterator<ULong> = this@asList.iterator()

        override fun lastIndexOf(element: ULong): Int = this@asList.lastIndexOf(element)
    }
}
