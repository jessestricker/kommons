/*
 * The source code in this file is auto-generated, do not edit manually.
 * Generator: kommons-immutable-array/generator/src/main/kotlin/Generator.kt
 */

@file:Suppress("detekt:MagicNumber", "detekt:ReturnCount", "detekt:TooManyFunctions")

package kommons

import kommons.internal.requireIndex

/** An immutable array of bytes. */
public class ImmutableByteArray
@PublishedApi
internal constructor(
    internal val data: ByteArray,
    internal val dataStart: Int = 0,
    internal val dataEnd: Int = data.size,
) {
    // 0 <= dataStart <= dataEnd <= data.size

    /**
     * Creates an immutable array of bytes of the given [size], with every element initialized to
     * zero.
     */
    public constructor(size: Int) : this(ByteArray(size))

    /** The number of elements. */
    public val size: Int
        get() = dataEnd - dataStart

    /**
     * Returns the element at the given [index].
     *
     * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
     */
    public operator fun get(index: Int): Byte {
        requireIndex(index in 0..<size) { "index $index must be within range 0..<$size" }
        return data[dataStart + index]
    }

    /** Returns an iterator over the elements. */
    public operator fun iterator(): ByteIterator {
        return object : ByteIterator() {
            private var dataIndex = dataStart

            override fun hasNext(): Boolean {
                return dataIndex < dataEnd
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
        return data.contentEquals(dataStart, dataEnd, other.data, other.dataStart, other.dataEnd)
    }

    override fun hashCode(): Int {
        return data.contentHashCode(dataStart, dataEnd)
    }

    override fun toString(): String {
        return "ImmutableByteArray(size=$size)"
    }
}

/**
 * Creates an immutable array of bytes of the given [size], with every element initialized by the
 * given [init] function.
 */
public inline fun ImmutableByteArray(size: Int, init: (index: Int) -> Byte): ImmutableByteArray {
    return ImmutableByteArray(ByteArray(size) { init(it) })
}

/** Creates an immutable array of bytes which contains the given [elements]. */
public fun immutableByteArrayOf(vararg elements: Byte): ImmutableByteArray {
    return ImmutableByteArray(elements)
}

/** Returns a new immutable array which contains the elements of this array. */
public fun ByteArray.toImmutableArray(): ImmutableByteArray {
    return ImmutableByteArray(this.copyOf())
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableByteArray.size].
 */
public fun ByteArray.toImmutableArray(startIndex: Int, endIndex: Int): ImmutableByteArray {
    return ImmutableByteArray(this.copyOfRange(startIndex, endIndex))
}

/** Returns a new mutable array which contains the elements of this array. */
public fun ImmutableByteArray.toMutableArray(): ByteArray {
    return data.copyOfRange(dataStart, dataEnd)
}

/**
 * Returns a new immutable array which contains the elements of this array from the given
 * [startIndex] (inclusive) to the given [endIndex] (exclusive).
 *
 * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
 * than [endIndex], or [endIndex] is greater than [size][ImmutableByteArray.size].
 */
public fun ImmutableByteArray.sliceArray(startIndex: Int, endIndex: Int): ImmutableByteArray {
    require(0 <= startIndex) { "startIndex $startIndex must be greater than or equal to 0" }
    require(startIndex <= endIndex) {
        "startIndex $startIndex must be less than or equal to endIndex $endIndex"
    }
    require(endIndex <= size) { "endIndex $endIndex must be less than or equal to size $size" }
    return ImmutableByteArray(data, dataStart + startIndex, dataStart + endIndex)
}

/** The last valid index. */
public val ImmutableByteArray.lastIndex: Int
    get() = size - 1

/** The range of valid indices. */
public val ImmutableByteArray.indices: IntRange
    get() = 0..<size

/** Returns whether this array is empty. */
public fun ImmutableByteArray.isEmpty(): Boolean {
    return size == 0
}

/** Returns whether this array is not empty. */
public fun ImmutableByteArray.isNotEmpty(): Boolean {
    return size != 0
}

/**
 * Returns the index of the first occurrence of the given [value] in this array, or -1 if this array
 * does not contain the given value.
 */
public fun ImmutableByteArray.indexOf(value: Byte): Int {
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
public fun ImmutableByteArray.lastIndexOf(value: Byte): Int {
    for (dataIndex in (dataStart..<dataEnd).reversed()) {
        if (value == data[dataIndex]) {
            return dataIndex - dataStart
        }
    }
    return -1
}

/** Returns whether this array contains the given [element]. */
public operator fun ImmutableByteArray.contains(element: Byte): Boolean {
    return indexOf(element) != -1
}

/** Returns an immutable [List] which contains the elements of this array. */
public fun ImmutableByteArray.asList(): List<Byte> {
    return object : AbstractList<Byte>(), RandomAccess {
        override val size: Int
            get() = this@asList.size

        override fun contains(element: Byte): Boolean = this@asList.contains(element)

        override fun get(index: Int): Byte = this@asList[index]

        override fun indexOf(element: Byte): Int = this@asList.indexOf(element)

        override fun isEmpty(): Boolean = this@asList.isEmpty()

        override fun iterator(): Iterator<Byte> = this@asList.iterator()

        override fun lastIndexOf(element: Byte): Int = this@asList.lastIndexOf(element)
    }
}
