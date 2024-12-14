package kommons

import kommons.internal.requireIndex

/** An immutable array of chars. */
public class ImmutableCharArray
internal constructor(
    internal val data: CharArray,
    internal val dataStart: Int = 0,
    internal val dataEnd: Int = data.size,
) {
    // 0 <= dataStart <= dataEnd <= data.size

    /** The number of elements. */
    public val size: Int
        get() = dataEnd - dataStart

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Char {
        requireIndex(index, size)
        return data[dataStart + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): CharIterator {
        return object : CharIterator() {
            private var dataIndex = dataStart

            override fun hasNext(): Boolean {
                return dataIndex < dataEnd
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
        return data.contentEquals(dataStart, dataEnd, other.data, other.dataStart, other.dataEnd)
    }

    override fun hashCode(): Int {
        return data.contentHashCode(dataStart, dataEnd)
    }

    override fun toString(): String {
        return "ImmutableCharArray(size=$size)"
    }
}

/**
 * Creates an immutable array of chars of the given [size], with every element initialized to the
 * null character U+0000.
 */
public fun ImmutableCharArray(size: Int): ImmutableCharArray {
    return ImmutableCharArray(CharArray(size))
}

/**
 * Creates an immutable array of chars of the given [size], with every element initialized by the
 * given [init] function.
 */
public fun ImmutableCharArray(size: Int, init: (index: Int) -> Char): ImmutableCharArray {
    return ImmutableCharArray(CharArray(size, init))
}

/** Creates a new immutable array of chars which contains the given [elements]. */
public fun immutableCharArrayOf(vararg elements: Char): ImmutableCharArray {
    return ImmutableCharArray(elements)
}

/** Returns a new immutable array which contains the elements of this array. */
public fun CharArray.toImmutableArray(): ImmutableCharArray {
    return ImmutableCharArray(this.copyOf())
}

/**
 * Returns a new immutable array which contains the elements of this array from given [startIndex]
 * (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableCharArray.size].
 */
public fun CharArray.toImmutableArray(startIndex: Int, endIndex: Int): ImmutableCharArray {
    return ImmutableCharArray(this.copyOfRange(startIndex, endIndex))
}

/** The range of valid indices. */
public val ImmutableCharArray.indices: IntRange
    get() = 0..<size

/** The last valid index. */
public val ImmutableCharArray.lastIndex: Int
    get() = size - 1

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
    for (dataIndex in dataStart..<dataEnd) {
        if (value == data[dataIndex]) {
            return dataIndex - dataStart
        }
    }
    return -1
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableCharArray.size].
 */
public fun ImmutableCharArray.sliceArray(startIndex: Int, endIndex: Int): ImmutableCharArray {
    // 0 <= startIndex <= endIndex <= size
    require(0 <= startIndex) { "startIndex $startIndex must be greater than or equal to 0" }
    require(startIndex <= endIndex) {
        "startIndex $startIndex must be less than or equal to endIndex $endIndex"
    }
    require(endIndex <= size) { "endIndex $endIndex must be less than or equal to size $size" }

    return ImmutableCharArray(data, dataStart + startIndex, dataStart + endIndex)
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableCharArray.toMutableArray(): CharArray {
    return data.copyOfRange(dataStart, dataEnd)
}
