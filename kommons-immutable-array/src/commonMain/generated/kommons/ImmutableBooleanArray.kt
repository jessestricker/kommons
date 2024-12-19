/*
 * The source code in this file is auto-generated, do not edit manually.
 * Generator: kommons-immutable-array/generator/src/main/kotlin/Generator.kt
 */

@file:Suppress("detekt:MagicNumber", "detekt:ReturnCount", "detekt:TooManyFunctions")

package kommons

import kommons.internal.requireIndex

/** An immutable array of booleans. */
public class ImmutableBooleanArray
@PublishedApi
internal constructor(
    internal val data: BooleanArray,
    internal val dataStart: Int = 0,
    internal val dataEnd: Int = data.size,
) {
    // 0 <= dataStart <= dataEnd <= data.size

    /**
     * Creates an immutable array of booleans of the given [size], with every element initialized to
     * `false`.
     */
    public constructor(size: Int) : this(BooleanArray(size))

    /** The number of elements. */
    public val size: Int
        get() = dataEnd - dataStart

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Boolean {
        requireIndex(index in 0..<size) { "index $index must be within range 0..<$size" }
        return data[dataStart + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): BooleanIterator {
        return object : BooleanIterator() {
            private var dataIndex = dataStart

            override fun hasNext(): Boolean {
                return dataIndex < dataEnd
            }

            override fun nextBoolean(): Boolean {
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
        other as ImmutableBooleanArray
        return data.contentEquals(dataStart, dataEnd, other.data, other.dataStart, other.dataEnd)
    }

    override fun hashCode(): Int {
        return data.contentHashCode(dataStart, dataEnd)
    }

    override fun toString(): String {
        return "ImmutableBooleanArray(size=$size)"
    }
}

/**
 * Creates an immutable array of booleans of the given [size], with every element initialized by the
 * given [init] function.
 */
public inline fun ImmutableBooleanArray(
    size: Int,
    init: (index: Int) -> Boolean,
): ImmutableBooleanArray {
    return ImmutableBooleanArray(BooleanArray(size) { init(it) })
}

/** Creates an immutable array of booleans which contains the given [elements]. */
public fun immutableBooleanArrayOf(vararg elements: Boolean): ImmutableBooleanArray {
    return ImmutableBooleanArray(elements)
}

/** Returns a new immutable array which contains the elements of this array. */
public fun BooleanArray.toImmutableArray(): ImmutableBooleanArray {
    return ImmutableBooleanArray(this.copyOf())
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableBooleanArray.size].
 */
public fun BooleanArray.toImmutableArray(startIndex: Int, endIndex: Int): ImmutableBooleanArray {
    return ImmutableBooleanArray(this.copyOfRange(startIndex, endIndex))
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableBooleanArray.toMutableArray(): BooleanArray {
    return data.copyOfRange(dataStart, dataEnd)
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableBooleanArray.size].
 */
public fun ImmutableBooleanArray.sliceArray(startIndex: Int, endIndex: Int): ImmutableBooleanArray {
    require(0 <= startIndex) { "startIndex $startIndex must be greater than or equal to 0" }
    require(startIndex <= endIndex) {
        "startIndex $startIndex must be less than or equal to endIndex $endIndex"
    }
    require(endIndex <= size) { "endIndex $endIndex must be less than or equal to size $size" }
    return ImmutableBooleanArray(data, dataStart + startIndex, dataStart + endIndex)
}

/** The last valid index. */
public val ImmutableBooleanArray.lastIndex: Int
    get() = size - 1

/** The range of valid indices. */
public val ImmutableBooleanArray.indices: IntRange
    get() = 0..<size

/** Returns whether this array is empty. */
public fun ImmutableBooleanArray.isEmpty(): Boolean {
    return size == 0
}

/** Returns whether this array is not empty. */
public fun ImmutableBooleanArray.isNotEmpty(): Boolean {
    return size != 0
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableBooleanArray.indexOf(value: Boolean): Int {
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
public fun ImmutableBooleanArray.lastIndexOf(value: Boolean): Int {
    for (dataIndex in (dataStart..<dataEnd).reversed()) {
        if (value == data[dataIndex]) {
            return dataIndex - dataStart
        }
    }
    return -1
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableBooleanArray.contains(element: Boolean): Boolean {
    return indexOf(element) != -1
}

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableBooleanArray.asList(): List<Boolean> {
    return object : AbstractList<Boolean>(), RandomAccess {
        override val size: Int
            get() = this@asList.size

        override fun contains(element: Boolean): Boolean = this@asList.contains(element)

        override fun get(index: Int): Boolean = this@asList[index]

        override fun indexOf(element: Boolean): Int = this@asList.indexOf(element)

        override fun isEmpty(): Boolean = this@asList.isEmpty()

        override fun iterator(): Iterator<Boolean> = this@asList.iterator()

        override fun lastIndexOf(element: Boolean): Int = this@asList.lastIndexOf(element)
    }
}
