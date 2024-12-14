package kommons

import kommons.internal.buildHashCodeOfRange
import kommons.internal.checkEqualsInRange
import kommons.internal.requireIndex
import kommons.internal.size

/** An immutable array of chars. */
public class ImmutableCharArray
internal constructor(
    internal val data: CharArray,
    internal val dataRange: IntRange = data.indices,
) {
    internal companion object {
        val EMPTY = ImmutableCharArray(CharArray(0))
    }

    /** The number of elements. */
    public val size: Int
        get() = dataRange.size

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Char {
        requireIndex(index, size)
        return data[dataRange.first + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): CharIterator {
        return object : CharIterator() {
            private var dataIndex = dataRange.first

            override fun hasNext(): Boolean {
                return dataIndex <= dataRange.last
            }

            override fun nextChar(): Char {
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
        other as ImmutableCharArray
        if (data === other.data && dataRange == other.dataRange) return true
        return checkEqualsInRange(dataRange, other.dataRange) { index, otherIndex ->
            data[index] == other.data[otherIndex]
        }
    }

    override fun hashCode(): Int {
        return buildHashCodeOfRange(dataRange) { index -> data[index].code }
    }

    override fun toString(): String {
        return "ImmutableCharArray(size=$size)"
    }
}

/**
 * Creates an immutable array of chars of the given [size], with every element initialized to
 * `'\u0000'`.
 */
public fun ImmutableCharArray(size: Int): ImmutableCharArray {
    return if (size == 0) {
        ImmutableCharArray.EMPTY
    } else {
        ImmutableCharArray(CharArray(size))
    }
}

/**
 * Creates an immutable array of chars of the given [size], with every element initialized by the
 * given [init] function.
 */
public fun ImmutableCharArray(size: Int, init: (index: Int) -> Char): ImmutableCharArray {
    return if (size == 0) {
        ImmutableCharArray.EMPTY
    } else {
        ImmutableCharArray(CharArray(size, init))
    }
}

/** Creates a new immutable array of chars which contains the given [elements]. */
public fun immutableCharArrayOf(vararg elements: Char): ImmutableCharArray {
    return if (elements.isEmpty()) {
        ImmutableCharArray.EMPTY
    } else {
        ImmutableCharArray(elements)
    }
}

/** Returns a new immutable array which contains the elements of this array. */
public fun CharArray.toImmutableArray(): ImmutableCharArray {
    return if (isEmpty()) {
        ImmutableCharArray.EMPTY
    } else {
        ImmutableCharArray(this.copyOf())
    }
}

/**
 * Returns a new immutable array which contains the elements of this array in the given [range].
 *
 * @throws[IndexOutOfBoundsException] if one of the endpoints of the given [range] is out of bounds.
 */
public fun CharArray.toImmutableArray(range: IntRange): ImmutableCharArray {
    return if (range.isEmpty()) {
        ImmutableCharArray.EMPTY
    } else {
        ImmutableCharArray(this.copyOfRange(range.first, range.last + 1))
    }
}

/** The range of valid indices. */
public val ImmutableCharArray.indices: IntRange
    get() = 0..lastIndex

/** The last valid index. */
public val ImmutableCharArray.lastIndex: Int
    get() = dataRange.last - dataRange.first

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableCharArray.asList(): List<Char> {
    return object : AbstractList<Char>() {
        override fun get(index: Int): Char {
            return this@asList[index]
        }

        override val size: Int
            get() = this@asList.size
    }
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableCharArray.contains(element: Char): Boolean {
    return indexOf(element) != -1
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableCharArray.indexOf(value: Char): Int {
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
public fun ImmutableCharArray.subArray(range: IntRange): ImmutableCharArray {
    requireIndex(range.first, size)
    requireIndex(range.last, size)
    return if (range.isEmpty()) {
        return ImmutableCharArray.EMPTY
    } else {
        val subDataRange = (dataRange.first + range.first)..(dataRange.first + range.last)
        ImmutableCharArray(data, subDataRange)
    }
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableCharArray.toMutableArray(): CharArray {
    return data.copyOfRange(dataRange.first, dataRange.last + 1)
}
