package kommons

import kommons.internal.buildHashCodeOfRange
import kommons.internal.checkEqualsInRange
import kommons.internal.requireIndex
import kommons.internal.size

/** An immutable array of booleans. */
public class ImmutableBooleanArray
internal constructor(
    internal val data: BooleanArray,
    internal val dataRange: IntRange = data.indices,
) {
    internal companion object {
        val EMPTY = ImmutableBooleanArray(BooleanArray(0))
    }

    /** The number of elements. */
    public val size: Int
        get() = dataRange.size

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Boolean {
        requireIndex(index, size)
        return data[dataRange.first + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): BooleanIterator {
        return object : BooleanIterator() {
            private var dataIndex = dataRange.first

            override fun hasNext(): Boolean {
                return dataIndex <= dataRange.last
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
        if (data === other.data && dataRange == other.dataRange) return true
        return checkEqualsInRange(dataRange, other.dataRange) { index, otherIndex ->
            data[index] == other.data[otherIndex]
        }
    }

    override fun hashCode(): Int {
        return buildHashCodeOfRange(dataRange) { index -> if (data[index]) 1231 else 1237 }
    }

    override fun toString(): String {
        return "ImmutableBooleanArray(size=$size)"
    }
}

/**
 * Creates an immutable array of booleans of the given [size], with every element initialized to
 * `false`.
 */
public fun ImmutableBooleanArray(size: Int): ImmutableBooleanArray {
    return if (size == 0) {
        ImmutableBooleanArray.EMPTY
    } else {
        ImmutableBooleanArray(BooleanArray(size))
    }
}

/**
 * Creates an immutable array of booleans of the given [size], with every element initialized by the
 * given [init] function.
 */
public fun ImmutableBooleanArray(size: Int, init: (index: Int) -> Boolean): ImmutableBooleanArray {
    return if (size == 0) {
        ImmutableBooleanArray.EMPTY
    } else {
        ImmutableBooleanArray(BooleanArray(size, init))
    }
}

/** Creates a new immutable array of booleans which contains the given [elements]. */
public fun immutableBooleanArrayOf(vararg elements: Boolean): ImmutableBooleanArray {
    return if (elements.isEmpty()) {
        ImmutableBooleanArray.EMPTY
    } else {
        ImmutableBooleanArray(elements)
    }
}

/** Returns a new immutable array which contains the elements of this array. */
public fun BooleanArray.toImmutableArray(): ImmutableBooleanArray {
    return if (isEmpty()) {
        ImmutableBooleanArray.EMPTY
    } else {
        ImmutableBooleanArray(this.copyOf())
    }
}

/**
 * Returns a new immutable array which contains the elements of this array in the given [range].
 *
 * @throws[IndexOutOfBoundsException] if one of the endpoints of the given [range] is out of bounds.
 */
public fun BooleanArray.toImmutableArray(range: IntRange): ImmutableBooleanArray {
    return if (range.isEmpty()) {
        ImmutableBooleanArray.EMPTY
    } else {
        ImmutableBooleanArray(this.copyOfRange(range.first, range.last + 1))
    }
}

/** The range of valid indices. */
public val ImmutableBooleanArray.indices: IntRange
    get() = 0..lastIndex

/** The last valid index. */
public val ImmutableBooleanArray.lastIndex: Int
    get() = dataRange.last - dataRange.first

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableBooleanArray.asList(): List<Boolean> {
    return object : AbstractList<Boolean>() {
        override fun get(index: Int): Boolean {
            return this@asList[index]
        }

        override val size: Int
            get() = this@asList.size
    }
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableBooleanArray.contains(element: Boolean): Boolean {
    return indexOf(element) != -1
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableBooleanArray.indexOf(value: Boolean): Int {
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
public fun ImmutableBooleanArray.subArray(range: IntRange): ImmutableBooleanArray {
    requireIndex(range.first, size)
    requireIndex(range.last, size)
    return if (range.isEmpty()) {
        return ImmutableBooleanArray.EMPTY
    } else {
        val subDataRange = (dataRange.first + range.first)..(dataRange.first + range.last)
        ImmutableBooleanArray(data, subDataRange)
    }
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableBooleanArray.toMutableArray(): BooleanArray {
    return data.copyOfRange(dataRange.first, dataRange.last + 1)
}
