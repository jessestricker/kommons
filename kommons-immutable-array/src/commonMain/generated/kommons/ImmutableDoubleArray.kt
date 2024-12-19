/*
 * The source code in this file is auto-generated, do not edit manually.
 * Generator: kommons-immutable-array/generator/src/main/kotlin/Generator.kt
 */

@file:Suppress("detekt:MagicNumber", "detekt:ReturnCount", "detekt:TooManyFunctions")

package kommons

import kommons.internal.requireIndex

/** An immutable array of doubles. */
public class ImmutableDoubleArray
@PublishedApi
internal constructor(
    internal val data: DoubleArray,
    internal val dataStart: Int = 0,
    internal val dataEnd: Int = data.size,
) {
    // 0 <= dataStart <= dataEnd <= data.size

    /**
     * Creates an immutable array of doubles of the given [size], with every element initialized to
     * zero.
     */
    public constructor(size: Int) : this(DoubleArray(size))

    /** The number of elements. */
    public val size: Int
        get() = dataEnd - dataStart

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Double {
        requireIndex(index in 0..<size) { "index $index must be within range 0..<$size" }
        return data[dataStart + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): DoubleIterator {
        return object : DoubleIterator() {
            private var dataIndex = dataStart

            override fun hasNext(): Boolean {
                return dataIndex < dataEnd
            }

            override fun nextDouble(): Double {
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
        other as ImmutableDoubleArray
        return data.contentEquals(dataStart, dataEnd, other.data, other.dataStart, other.dataEnd)
    }

    override fun hashCode(): Int {
        return data.contentHashCode(dataStart, dataEnd)
    }

    override fun toString(): String {
        return "ImmutableDoubleArray(size=$size)"
    }
}

/**
 * Creates an immutable array of doubles of the given [size], with every element initialized by the
 * given [init] function.
 */
public inline fun ImmutableDoubleArray(
    size: Int,
    init: (index: Int) -> Double,
): ImmutableDoubleArray {
    return ImmutableDoubleArray(DoubleArray(size) { init(it) })
}

/** Creates an immutable array of doubles which contains the given [elements]. */
public fun immutableDoubleArrayOf(vararg elements: Double): ImmutableDoubleArray {
    return ImmutableDoubleArray(elements)
}

/** Returns a new immutable array which contains the elements of this array. */
public fun DoubleArray.toImmutableArray(): ImmutableDoubleArray {
    return ImmutableDoubleArray(this.copyOf())
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableDoubleArray.size].
 */
public fun DoubleArray.toImmutableArray(startIndex: Int, endIndex: Int): ImmutableDoubleArray {
    return ImmutableDoubleArray(this.copyOfRange(startIndex, endIndex))
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableDoubleArray.toMutableArray(): DoubleArray {
    return data.copyOfRange(dataStart, dataEnd)
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableDoubleArray.size].
 */
public fun ImmutableDoubleArray.sliceArray(startIndex: Int, endIndex: Int): ImmutableDoubleArray {
    require(0 <= startIndex) { "startIndex $startIndex must be greater than or equal to 0" }
    require(startIndex <= endIndex) {
        "startIndex $startIndex must be less than or equal to endIndex $endIndex"
    }
    require(endIndex <= size) { "endIndex $endIndex must be less than or equal to size $size" }
    return ImmutableDoubleArray(data, dataStart + startIndex, dataStart + endIndex)
}

/** The last valid index. */
public val ImmutableDoubleArray.lastIndex: Int
    get() = size - 1

/** The range of valid indices. */
public val ImmutableDoubleArray.indices: IntRange
    get() = 0..<size

/** Returns whether this array is empty. */
public fun ImmutableDoubleArray.isEmpty(): Boolean {
    return size == 0
}

/** Returns whether this array is not empty. */
public fun ImmutableDoubleArray.isNotEmpty(): Boolean {
    return size != 0
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableDoubleArray.indexOf(value: Double): Int {
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
public fun ImmutableDoubleArray.lastIndexOf(value: Double): Int {
    for (dataIndex in (dataStart..<dataEnd).reversed()) {
        if (value == data[dataIndex]) {
            return dataIndex - dataStart
        }
    }
    return -1
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableDoubleArray.contains(element: Double): Boolean {
    return indexOf(element) != -1
}

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableDoubleArray.asList(): List<Double> {
    return object : AbstractList<Double>(), RandomAccess {
        override val size: Int
            get() = this@asList.size

        override fun contains(element: Double): Boolean = this@asList.contains(element)

        override fun get(index: Int): Double = this@asList[index]

        override fun indexOf(element: Double): Int = this@asList.indexOf(element)

        override fun isEmpty(): Boolean = this@asList.isEmpty()

        override fun iterator(): Iterator<Double> = this@asList.iterator()

        override fun lastIndexOf(element: Double): Int = this@asList.lastIndexOf(element)
    }
}
