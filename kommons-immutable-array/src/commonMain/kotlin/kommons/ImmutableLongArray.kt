package kommons

import kommons.internal.buildHashCodeOfRange
import kommons.internal.checkEqualsInRange
import kommons.internal.requireIndex
import kommons.internal.size

/** An immutable array of longs. */
public class ImmutableLongArray
internal constructor(
    internal val data: LongArray,
    internal val dataRange: IntRange = data.indices,
) {
    internal companion object {
        val EMPTY = ImmutableLongArray(LongArray(0))
    }

    /** The number of elements. */
    public val size: Int
        get() = dataRange.size

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Long {
        requireIndex(index, size)
        return data[dataRange.first + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): LongIterator {
        return object : LongIterator() {
            private var dataIndex = dataRange.first

            override fun hasNext(): Boolean {
                return dataIndex <= dataRange.last
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
        if (data === other.data && dataRange == other.dataRange) return true
        return checkEqualsInRange(dataRange, other.dataRange) { index, otherIndex ->
            data[index] == other.data[otherIndex]
        }
    }

    override fun hashCode(): Int {
        return buildHashCodeOfRange(dataRange) { index ->
            val element = data[index]
            (element xor (element ushr 32)).toInt()
        }
    }

    override fun toString(): String {
        return "ImmutableLongArray(size=$size)"
    }
}

/**
 * Creates an immutable array of longs of the given [size], with every element initialized to `0L`.
 */
public fun ImmutableLongArray(size: Int): ImmutableLongArray {
    return if (size == 0) {
        ImmutableLongArray.EMPTY
    } else {
        ImmutableLongArray(LongArray(size))
    }
}

/**
 * Creates an immutable array of longs of the given [size], with every element initialized by the
 * given [init] function.
 */
public fun ImmutableLongArray(size: Int, init: (index: Int) -> Long): ImmutableLongArray {
    return if (size == 0) {
        ImmutableLongArray.EMPTY
    } else {
        ImmutableLongArray(LongArray(size, init))
    }
}

/** Creates a new immutable array of longs which contains the given [elements]. */
public fun immutableLongArrayOf(vararg elements: Long): ImmutableLongArray {
    return if (elements.isEmpty()) {
        ImmutableLongArray.EMPTY
    } else {
        ImmutableLongArray(elements)
    }
}

/** Returns a new immutable array which contains the elements of this array. */
public fun LongArray.toImmutableArray(): ImmutableLongArray {
    return if (isEmpty()) {
        ImmutableLongArray.EMPTY
    } else {
        ImmutableLongArray(this.copyOf())
    }
}

/**
 * Returns a new immutable array which contains the elements of this array in the given [range].
 *
 * @throws[IndexOutOfBoundsException] if one of the endpoints of the given [range] is out of bounds.
 */
public fun LongArray.toImmutableArray(range: IntRange): ImmutableLongArray {
    return if (range.isEmpty()) {
        ImmutableLongArray.EMPTY
    } else {
        ImmutableLongArray(this.copyOfRange(range.first, range.last + 1))
    }
}

/** The range of valid indices. */
public val ImmutableLongArray.indices: IntRange
    get() = 0..lastIndex

/** The last valid index. */
public val ImmutableLongArray.lastIndex: Int
    get() = dataRange.last - dataRange.first

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableLongArray.asList(): List<Long> {
    return object : AbstractList<Long>() {
        override fun get(index: Int): Long {
            return this@asList[index]
        }

        override val size: Int
            get() = this@asList.size
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
public fun ImmutableLongArray.subArray(range: IntRange): ImmutableLongArray {
    requireIndex(range.first, size)
    requireIndex(range.last, size)
    return if (range.isEmpty()) {
        return ImmutableLongArray.EMPTY
    } else {
        val subDataRange = (dataRange.first + range.first)..(dataRange.first + range.last)
        ImmutableLongArray(data, subDataRange)
    }
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableLongArray.toMutableArray(): LongArray {
    return data.copyOfRange(dataRange.first, dataRange.last + 1)
}
