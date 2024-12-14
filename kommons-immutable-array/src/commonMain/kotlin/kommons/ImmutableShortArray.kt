package kommons

import kommons.internal.buildHashCodeOfRange
import kommons.internal.checkEqualsInRange
import kommons.internal.requireIndex
import kommons.internal.size

/** An immutable array of shorts. */
public class ImmutableShortArray
internal constructor(
    internal val data: ShortArray,
    internal val dataRange: IntRange = data.indices,
) {
    internal companion object {
        val EMPTY = ImmutableShortArray(ShortArray(0))
    }

    /** The number of elements. */
    public val size: Int
        get() = dataRange.size

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Short {
        requireIndex(index, size)
        return data[dataRange.first + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): ShortIterator {
        return object : ShortIterator() {
            private var dataIndex = dataRange.first

            override fun hasNext(): Boolean {
                return dataIndex <= dataRange.last
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
        if (data === other.data && dataRange == other.dataRange) return true
        return checkEqualsInRange(dataRange, other.dataRange) { index, otherIndex ->
            data[index] == other.data[otherIndex]
        }
    }

    override fun hashCode(): Int {
        return buildHashCodeOfRange(dataRange) { index -> data[index].toInt() }
    }

    override fun toString(): String {
        return "ImmutableShortArray(size=$size)"
    }
}

/**
 * Creates an immutable array of shorts of the given [size], with every element initialized to `0`.
 */
public fun ImmutableShortArray(size: Int): ImmutableShortArray {
    return if (size == 0) {
        ImmutableShortArray.EMPTY
    } else {
        ImmutableShortArray(ShortArray(size))
    }
}

/**
 * Creates an immutable array of shorts of the given [size], with every element initialized by the
 * given [init] function.
 */
public fun ImmutableShortArray(size: Int, init: (index: Int) -> Short): ImmutableShortArray {
    return if (size == 0) {
        ImmutableShortArray.EMPTY
    } else {
        ImmutableShortArray(ShortArray(size, init))
    }
}

/** Creates a new immutable array of shorts which contains the given [elements]. */
public fun immutableShortArrayOf(vararg elements: Short): ImmutableShortArray {
    return if (elements.isEmpty()) {
        ImmutableShortArray.EMPTY
    } else {
        ImmutableShortArray(elements)
    }
}

/** Returns a new immutable array which contains the elements of this array. */
public fun ShortArray.toImmutableArray(): ImmutableShortArray {
    return if (isEmpty()) {
        ImmutableShortArray.EMPTY
    } else {
        ImmutableShortArray(this.copyOf())
    }
}

/**
 * Returns a new immutable array which contains the elements of this array in the given [range].
 *
 * @throws[IndexOutOfBoundsException] if one of the endpoints of the given [range] is out of bounds.
 */
public fun ShortArray.toImmutableArray(range: IntRange): ImmutableShortArray {
    return if (range.isEmpty()) {
        ImmutableShortArray.EMPTY
    } else {
        ImmutableShortArray(this.copyOfRange(range.first, range.last + 1))
    }
}

/** The range of valid indices. */
public val ImmutableShortArray.indices: IntRange
    get() = 0..lastIndex

/** The last valid index. */
public val ImmutableShortArray.lastIndex: Int
    get() = dataRange.last - dataRange.first

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableShortArray.asList(): List<Short> {
    return object : AbstractList<Short>() {
        override fun get(index: Int): Short {
            return this@asList[index]
        }

        override val size: Int
            get() = this@asList.size
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
public fun ImmutableShortArray.subArray(range: IntRange): ImmutableShortArray {
    requireIndex(range.first, size)
    requireIndex(range.last, size)
    return if (range.isEmpty()) {
        return ImmutableShortArray.EMPTY
    } else {
        val subDataRange = (dataRange.first + range.first)..(dataRange.first + range.last)
        ImmutableShortArray(data, subDataRange)
    }
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableShortArray.toMutableArray(): ShortArray {
    return data.copyOfRange(dataRange.first, dataRange.last + 1)
}
