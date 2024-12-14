package kommons

import kommons.internal.buildHashCodeOfRange
import kommons.internal.checkEqualsInRange
import kommons.internal.requireIndex
import kommons.internal.size

/** An immutable array of doubles. */
public class ImmutableDoubleArray
internal constructor(
    internal val data: DoubleArray,
    internal val dataRange: IntRange = data.indices,
) {
    internal companion object {
        val EMPTY = ImmutableDoubleArray(DoubleArray(0))
    }

    /** The number of elements. */
    public val size: Int
        get() = dataRange.size

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Double {
        requireIndex(index, size)
        return data[dataRange.first + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): DoubleIterator {
        return object : DoubleIterator() {
            private var dataIndex = dataRange.first

            override fun hasNext(): Boolean {
                return dataIndex <= dataRange.last
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
        if (data === other.data && dataRange == other.dataRange) return true
        return checkEqualsInRange(dataRange, other.dataRange) { index, otherIndex ->
            data[index] == other.data[otherIndex]
        }
    }

    override fun hashCode(): Int {
        return buildHashCodeOfRange(dataRange) { index ->
            val bits = data[index].toBits()
            (bits xor (bits ushr 32)).toInt()
        }
    }

    override fun toString(): String {
        return "ImmutableDoubleArray(size=$size)"
    }
}

/**
 * Creates an immutable array of doubles of the given [size], with every element initialized to
 * `0.0`.
 */
public fun ImmutableDoubleArray(size: Int): ImmutableDoubleArray {
    return if (size == 0) {
        ImmutableDoubleArray.EMPTY
    } else {
        ImmutableDoubleArray(DoubleArray(size))
    }
}

/**
 * Creates an immutable array of doubles of the given [size], with every element initialized by the
 * given [init] function.
 */
public fun ImmutableDoubleArray(size: Int, init: (index: Int) -> Double): ImmutableDoubleArray {
    return if (size == 0) {
        ImmutableDoubleArray.EMPTY
    } else {
        ImmutableDoubleArray(DoubleArray(size, init))
    }
}

/** Creates a new immutable array of doubles which contains the given [elements]. */
public fun immutableDoubleArrayOf(vararg elements: Double): ImmutableDoubleArray {
    return if (elements.isEmpty()) {
        ImmutableDoubleArray.EMPTY
    } else {
        ImmutableDoubleArray(elements)
    }
}

/** Returns a new immutable array which contains the elements of this array. */
public fun DoubleArray.toImmutableArray(): ImmutableDoubleArray {
    return if (isEmpty()) {
        ImmutableDoubleArray.EMPTY
    } else {
        ImmutableDoubleArray(this.copyOf())
    }
}

/**
 * Returns a new immutable array which contains the elements of this array in the given [range].
 *
 * @throws[IndexOutOfBoundsException] if one of the endpoints of the given [range] is out of bounds.
 */
public fun DoubleArray.toImmutableArray(range: IntRange): ImmutableDoubleArray {
    return if (range.isEmpty()) {
        ImmutableDoubleArray.EMPTY
    } else {
        ImmutableDoubleArray(this.copyOfRange(range.first, range.last + 1))
    }
}

/** The range of valid indices. */
public val ImmutableDoubleArray.indices: IntRange
    get() = 0..lastIndex

/** The last valid index. */
public val ImmutableDoubleArray.lastIndex: Int
    get() = dataRange.last - dataRange.first

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableDoubleArray.asList(): List<Double> {
    return object : AbstractList<Double>() {
        override fun get(index: Int): Double {
            return this@asList[index]
        }

        override val size: Int
            get() = this@asList.size
    }
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableDoubleArray.contains(element: Double): Boolean {
    return indexOf(element) != -1
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableDoubleArray.indexOf(value: Double): Int {
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
public fun ImmutableDoubleArray.subArray(range: IntRange): ImmutableDoubleArray {
    requireIndex(range.first, size)
    requireIndex(range.last, size)
    return if (range.isEmpty()) {
        return ImmutableDoubleArray.EMPTY
    } else {
        val subDataRange = (dataRange.first + range.first)..(dataRange.first + range.last)
        ImmutableDoubleArray(data, subDataRange)
    }
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableDoubleArray.toMutableArray(): DoubleArray {
    return data.copyOfRange(dataRange.first, dataRange.last + 1)
}
