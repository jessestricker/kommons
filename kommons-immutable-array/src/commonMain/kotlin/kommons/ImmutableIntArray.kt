package kommons

import kommons.internal.buildHashCodeOfRange
import kommons.internal.checkEqualsInRange
import kommons.internal.requireIndex
import kommons.internal.size

/** An immutable array of ints. */
public class ImmutableIntArray
internal constructor(internal val data: IntArray, internal val dataRange: IntRange = data.indices) {
    internal companion object {
        val EMPTY = ImmutableIntArray(IntArray(0))
    }

    /** The number of elements. */
    public val size: Int
        get() = dataRange.size

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Int {
        requireIndex(index, size)
        return data[dataRange.first + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): IntIterator {
        return object : IntIterator() {
            private var dataIndex = dataRange.first

            override fun hasNext(): Boolean {
                return dataIndex <= dataRange.last
            }

            override fun nextInt(): Int {
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
        other as ImmutableIntArray
        if (data === other.data && dataRange == other.dataRange) return true
        return checkEqualsInRange(dataRange, other.dataRange) { index, otherIndex ->
            data[index] == other.data[otherIndex]
        }
    }

    override fun hashCode(): Int {
        return buildHashCodeOfRange(dataRange) { index -> data[index] }
    }

    override fun toString(): String {
        return "ImmutableIntArray(size=$size)"
    }
}

/**
 * Creates an immutable array of ints of the given [size], with every element initialized to `0`.
 */
public fun ImmutableIntArray(size: Int): ImmutableIntArray {
    return if (size == 0) {
        ImmutableIntArray.EMPTY
    } else {
        ImmutableIntArray(IntArray(size))
    }
}

/**
 * Creates an immutable array of ints of the given [size], with every element initialized by the
 * given [init] function.
 */
public fun ImmutableIntArray(size: Int, init: (index: Int) -> Int): ImmutableIntArray {
    return if (size == 0) {
        ImmutableIntArray.EMPTY
    } else {
        ImmutableIntArray(IntArray(size, init))
    }
}

/** Creates a new immutable array of ints which contains the given [elements]. */
public fun immutableIntArrayOf(vararg elements: Int): ImmutableIntArray {
    return if (elements.isEmpty()) {
        ImmutableIntArray.EMPTY
    } else {
        ImmutableIntArray(elements)
    }
}

/** Returns a new immutable array which contains the elements of this array. */
public fun IntArray.toImmutableArray(): ImmutableIntArray {
    return if (isEmpty()) {
        ImmutableIntArray.EMPTY
    } else {
        ImmutableIntArray(this.copyOf())
    }
}

/**
 * Returns a new immutable array which contains the elements of this array in the given [range].
 *
 * @throws[IndexOutOfBoundsException] if one of the endpoints of the given [range] is out of bounds.
 */
public fun IntArray.toImmutableArray(range: IntRange): ImmutableIntArray {
    return if (range.isEmpty()) {
        ImmutableIntArray.EMPTY
    } else {
        ImmutableIntArray(this.copyOfRange(range.first, range.last + 1))
    }
}

/** The range of valid indices. */
public val ImmutableIntArray.indices: IntRange
    get() = 0..lastIndex

/** The last valid index. */
public val ImmutableIntArray.lastIndex: Int
    get() = dataRange.last - dataRange.first

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableIntArray.asList(): List<Int> {
    return object : AbstractList<Int>() {
        override fun get(index: Int): Int {
            return this@asList[index]
        }

        override val size: Int
            get() = this@asList.size
    }
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableIntArray.contains(element: Int): Boolean {
    return indexOf(element) != -1
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableIntArray.indexOf(value: Int): Int {
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
public fun ImmutableIntArray.subArray(range: IntRange): ImmutableIntArray {
    requireIndex(range.first, size)
    requireIndex(range.last, size)
    return if (range.isEmpty()) {
        return ImmutableIntArray.EMPTY
    } else {
        val subDataRange = (dataRange.first + range.first)..(dataRange.first + range.last)
        ImmutableIntArray(data, subDataRange)
    }
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableIntArray.toMutableArray(): IntArray {
    return data.copyOfRange(dataRange.first, dataRange.last + 1)
}
