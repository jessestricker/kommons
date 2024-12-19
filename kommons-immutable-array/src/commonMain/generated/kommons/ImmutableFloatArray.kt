/*
 * The source code in this file is auto-generated, do not edit manually.
 * Generator: kommons-immutable-array/generator/src/main/kotlin/Generator.kt
 */

@file:Suppress("detekt:MagicNumber", "detekt:ReturnCount", "detekt:TooManyFunctions")

package kommons

import kommons.internal.requireIndex

/** An immutable array of floats. */
public class ImmutableFloatArray
@PublishedApi
internal constructor(
    internal val data: FloatArray,
    internal val dataStart: Int = 0,
    internal val dataEnd: Int = data.size,
) {
    // 0 <= dataStart <= dataEnd <= data.size

    /**
     * Creates an immutable array of floats of the given [size], with every element initialized to
     * zero.
     */
    public constructor(size: Int) : this(FloatArray(size))

    /** The number of elements. */
    public val size: Int
        get() = dataEnd - dataStart

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Float {
        requireIndex(index in 0..<size) { "index $index must be within range 0..<$size" }
        return data[dataStart + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): FloatIterator {
        return object : FloatIterator() {
            private var dataIndex = dataStart

            override fun hasNext(): Boolean {
                return dataIndex < dataEnd
            }

            override fun nextFloat(): Float {
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
        other as ImmutableFloatArray
        return data.contentEquals(dataStart, dataEnd, other.data, other.dataStart, other.dataEnd)
    }

    override fun hashCode(): Int {
        return data.contentHashCode(dataStart, dataEnd)
    }

    override fun toString(): String {
        return "ImmutableFloatArray(size=$size)"
    }
}

/**
 * Creates an immutable array of floats of the given [size], with every element initialized by the
 * given [init] function.
 */
public inline fun ImmutableFloatArray(size: Int, init: (index: Int) -> Float): ImmutableFloatArray {
    return ImmutableFloatArray(FloatArray(size) { init(it) })
}

/** Creates an immutable array of floats which contains the given [elements]. */
public fun immutableFloatArrayOf(vararg elements: Float): ImmutableFloatArray {
    return ImmutableFloatArray(elements)
}

/** Returns a new immutable array which contains the elements of this array. */
public fun FloatArray.toImmutableArray(): ImmutableFloatArray {
    return ImmutableFloatArray(this.copyOf())
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableFloatArray.size].
 */
public fun FloatArray.toImmutableArray(startIndex: Int, endIndex: Int): ImmutableFloatArray {
    return ImmutableFloatArray(this.copyOfRange(startIndex, endIndex))
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableFloatArray.toMutableArray(): FloatArray {
    return data.copyOfRange(dataStart, dataEnd)
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableFloatArray.size].
 */
public fun ImmutableFloatArray.sliceArray(startIndex: Int, endIndex: Int): ImmutableFloatArray {
    require(0 <= startIndex) { "startIndex $startIndex must be greater than or equal to 0" }
    require(startIndex <= endIndex) {
        "startIndex $startIndex must be less than or equal to endIndex $endIndex"
    }
    require(endIndex <= size) { "endIndex $endIndex must be less than or equal to size $size" }
    return ImmutableFloatArray(data, dataStart + startIndex, dataStart + endIndex)
}

/** The last valid index. */
public val ImmutableFloatArray.lastIndex: Int
    get() = size - 1

/** The range of valid indices. */
public val ImmutableFloatArray.indices: IntRange
    get() = 0..<size

/** Returns whether this array is empty. */
public fun ImmutableFloatArray.isEmpty(): Boolean {
    return size == 0
}

/** Returns whether this array is not empty. */
public fun ImmutableFloatArray.isNotEmpty(): Boolean {
    return size != 0
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableFloatArray.indexOf(value: Float): Int {
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
public fun ImmutableFloatArray.lastIndexOf(value: Float): Int {
    for (dataIndex in (dataStart..<dataEnd).reversed()) {
        if (value == data[dataIndex]) {
            return dataIndex - dataStart
        }
    }
    return -1
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableFloatArray.contains(element: Float): Boolean {
    return indexOf(element) != -1
}

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableFloatArray.asList(): List<Float> {
    return object : AbstractList<Float>(), RandomAccess {
        override val size: Int
            get() = this@asList.size

        override fun contains(element: Float): Boolean = this@asList.contains(element)

        override fun get(index: Int): Float = this@asList[index]

        override fun indexOf(element: Float): Int = this@asList.indexOf(element)

        override fun isEmpty(): Boolean = this@asList.isEmpty()

        override fun iterator(): Iterator<Float> = this@asList.iterator()

        override fun lastIndexOf(element: Float): Int = this@asList.lastIndexOf(element)
    }
}
