package kommons

import kommons.internal.buildHashCodeOfRange
import kommons.internal.checkEqualsInRange
import kommons.internal.requireIndex
import kommons.internal.size

/** An immutable array of floats. */
public class ImmutableFloatArray
internal constructor(
    internal val data: FloatArray,
    internal val dataRange: IntRange = data.indices,
) {
    internal companion object {
        val EMPTY = ImmutableFloatArray(FloatArray(0))
    }

    /** The number of elements. */
    public val size: Int
        get() = dataRange.size

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Float {
        requireIndex(index, size)
        return data[dataRange.first + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): FloatIterator {
        return object : FloatIterator() {
            private var dataIndex = dataRange.first

            override fun hasNext(): Boolean {
                return dataIndex <= dataRange.last
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
        if (data === other.data && dataRange == other.dataRange) return true
        return checkEqualsInRange(dataRange, other.dataRange) { index, otherIndex ->
            data[index] == other.data[otherIndex]
        }
    }

    override fun hashCode(): Int {
        return buildHashCodeOfRange(dataRange) { index -> data[index].toBits() }
    }

    override fun toString(): String {
        return "ImmutableFloatArray(size=$size)"
    }
}

/**
 * Creates an immutable array of floats of the given [size], with every element initialized to
 * `0.0f`.
 */
public fun ImmutableFloatArray(size: Int): ImmutableFloatArray {
    return if (size == 0) {
        ImmutableFloatArray.EMPTY
    } else {
        ImmutableFloatArray(FloatArray(size))
    }
}

/**
 * Creates an immutable array of floats of the given [size], with every element initialized by the
 * given [init] function.
 */
public fun ImmutableFloatArray(size: Int, init: (index: Int) -> Float): ImmutableFloatArray {
    return if (size == 0) {
        ImmutableFloatArray.EMPTY
    } else {
        ImmutableFloatArray(FloatArray(size, init))
    }
}

/** Creates a new immutable array of floats which contains the given [elements]. */
public fun immutableFloatArrayOf(vararg elements: Float): ImmutableFloatArray {
    return if (elements.isEmpty()) {
        ImmutableFloatArray.EMPTY
    } else {
        ImmutableFloatArray(elements)
    }
}

/** Returns a new immutable array which contains the elements of this array. */
public fun FloatArray.toImmutableArray(): ImmutableFloatArray {
    return if (isEmpty()) {
        ImmutableFloatArray.EMPTY
    } else {
        ImmutableFloatArray(this.copyOf())
    }
}

/**
 * Returns a new immutable array which contains the elements of this array in the given [range].
 *
 * @throws[IndexOutOfBoundsException] if one of the endpoints of the given [range] is out of bounds.
 */
public fun FloatArray.toImmutableArray(range: IntRange): ImmutableFloatArray {
    return if (range.isEmpty()) {
        ImmutableFloatArray.EMPTY
    } else {
        ImmutableFloatArray(this.copyOfRange(range.first, range.last + 1))
    }
}

/** The range of valid indices. */
public val ImmutableFloatArray.indices: IntRange
    get() = 0..lastIndex

/** The last valid index. */
public val ImmutableFloatArray.lastIndex: Int
    get() = dataRange.last - dataRange.first

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableFloatArray.asList(): List<Float> {
    return object : AbstractList<Float>() {
        override fun get(index: Int): Float {
            return this@asList[index]
        }

        override val size: Int
            get() = this@asList.size
    }
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableFloatArray.contains(element: Float): Boolean {
    return indexOf(element) != -1
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableFloatArray.indexOf(value: Float): Int {
    for (dataIndex in dataRange) {
        if (value == data[dataIndex]) {
            return dataIndex - dataRange.first
        }
    }
    return -1
}

/**
 * Returns a new immutable array which contains the elements of this array in the given [range].
 *
 * @throws IndexOutOfBoundsException if one of the endpoints of the given [range] is out of bounds.
 */
public fun ImmutableFloatArray.subArray(range: IntRange): ImmutableFloatArray {
    requireIndex(range.first, size)
    requireIndex(range.last, size)
    return if (range.isEmpty()) {
        return ImmutableFloatArray.EMPTY
    } else {
        val subDataRange = (dataRange.first + range.first)..(dataRange.first + range.last)
        ImmutableFloatArray(data, subDataRange)
    }
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableFloatArray.toMutableArray(): FloatArray {
    return data.copyOfRange(dataRange.first, dataRange.last + 1)
}
