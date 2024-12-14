package kommons

import kommons.internal.buildHashCodeOfRange
import kommons.internal.checkEqualsInRange
import kommons.internal.requireIndex
import kommons.internal.size

/** An immutable array of bytes. */
public class ImmutableByteArray
internal constructor(
    internal val data: ByteArray,
    internal val dataRange: IntRange = data.indices,
) {
    internal companion object {
        val EMPTY = ImmutableByteArray(ByteArray(0))
    }

    /** The number of elements. */
    public val size: Int
        get() = dataRange.size

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Byte {
        requireIndex(index, size)
        return data[dataRange.first + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): ByteIterator {
        return object : ByteIterator() {
            private var dataIndex = dataRange.first

            override fun hasNext(): Boolean {
                return dataIndex <= dataRange.last
            }

            override fun nextByte(): Byte {
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
        other as ImmutableByteArray
        if (data === other.data && dataRange == other.dataRange) return true
        return checkEqualsInRange(dataRange, other.dataRange) { index, otherIndex ->
            data[index] == other.data[otherIndex]
        }
    }

    override fun hashCode(): Int {
        return buildHashCodeOfRange(dataRange) { index -> data[index].toInt() }
    }

    override fun toString(): String {
        return "ImmutableByteArray(size=$size)"
    }
}

/**
 * Creates an immutable array of bytes of the given [size], with every element initialized to `0`.
 */
public fun ImmutableByteArray(size: Int): ImmutableByteArray {
    return if (size == 0) {
        ImmutableByteArray.EMPTY
    } else {
        ImmutableByteArray(ByteArray(size))
    }
}

/**
 * Creates an immutable array of bytes of the given [size], with every element initialized by the
 * given [init] function.
 */
public fun ImmutableByteArray(size: Int, init: (index: Int) -> Byte): ImmutableByteArray {
    return if (size == 0) {
        ImmutableByteArray.EMPTY
    } else {
        ImmutableByteArray(ByteArray(size, init))
    }
}

/** Creates a new immutable array of bytes which contains the given [elements]. */
public fun immutableByteArrayOf(vararg elements: Byte): ImmutableByteArray {
    return if (elements.isEmpty()) {
        ImmutableByteArray.EMPTY
    } else {
        ImmutableByteArray(elements)
    }
}

/** Returns a new immutable array which contains the elements of this array. */
public fun ByteArray.toImmutableArray(): ImmutableByteArray {
    return if (isEmpty()) {
        ImmutableByteArray.EMPTY
    } else {
        ImmutableByteArray(this.copyOf())
    }
}

/**
 * Returns a new immutable array which contains the elements of this array in the given [range].
 *
 * @throws[IndexOutOfBoundsException] if one of the endpoints of the given [range] is out of bounds.
 */
public fun ByteArray.toImmutableArray(range: IntRange): ImmutableByteArray {
    return if (range.isEmpty()) {
        ImmutableByteArray.EMPTY
    } else {
        ImmutableByteArray(this.copyOfRange(range.first, range.last + 1))
    }
}

/** The range of valid indices. */
public val ImmutableByteArray.indices: IntRange
    get() = 0..lastIndex

/** The last valid index. */
public val ImmutableByteArray.lastIndex: Int
    get() = dataRange.last - dataRange.first

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableByteArray.asList(): List<Byte> {
    return object : AbstractList<Byte>() {
        override fun get(index: Int): Byte {
            return this@asList[index]
        }

        override val size: Int
            get() = this@asList.size
    }
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableByteArray.contains(element: Byte): Boolean {
    return indexOf(element) != -1
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableByteArray.indexOf(value: Byte): Int {
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
public fun ImmutableByteArray.subArray(range: IntRange): ImmutableByteArray {
    requireIndex(range.first, size)
    requireIndex(range.last, size)
    return if (range.isEmpty()) {
        return ImmutableByteArray.EMPTY
    } else {
        val subDataRange = (dataRange.first + range.first)..(dataRange.first + range.last)
        ImmutableByteArray(data, subDataRange)
    }
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableByteArray.toMutableArray(): ByteArray {
    return data.copyOfRange(dataRange.first, dataRange.last + 1)
}
