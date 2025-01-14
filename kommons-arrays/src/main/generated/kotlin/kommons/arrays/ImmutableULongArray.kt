/*
 * This file is auto-generated, do not edit!
 */

@file:Suppress("TooManyFunctions")

package kommons.arrays

/**
 * An immutable array of unsigned longs.
 *
 * When targeting the JVM, instances of this class are represented as `long[]`.
 *
 * @see ULongArray
 */
@ExperimentalUnsignedTypes
@JvmInline
public value class ImmutableULongArray internal constructor(internal val data: ULongArray) {
    /**
     * Creates a new immutable array of the specified [size], with all elements initialized to zero.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public constructor(size: Int) : this(ULongArray(size))

    /**
     * Creates a new immutable array of the specified [size], where each element is calculated by
     * calling the specified [init] function.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public constructor(size: Int, init: (index: Int) -> ULong) : this(ULongArray(size, init))

    /** The number of elements. */
    public val size: Int
        get() = data.size

    /**
     * Returns the element at the given [index].
     *
     * @throws IndexOutOfBoundsException if the [index] is out of bounds.
     */
    public operator fun get(index: Int): ULong {
        return data[index]
    }

    /**
     * Creates a specialized iterator for iterating over the elements.
     *
     * @see Iterator<ULong>
     */
    public operator fun iterator(): Iterator<ULong> {
        return data.iterator()
    }

    public companion object {
        /**
         * Returns a new immutable wrapping the given [mutable array][mutableArray].
         *
         * **Note:** This function is marked as _delicate_ because it could potentially break the
         * contract of immutability. Only use this function if you can ensure that the given
         * [mutable array][mutableArray] is not modified during the lifetime of the returned
         * immutable array.
         */
        @DelicateArraysApi
        public fun wrap(mutableArray: ULongArray): ImmutableULongArray {
            return ImmutableULongArray(mutableArray)
        }
    }
}

/** Returns a new immutable array which contains the given [elements]. */
@ExperimentalUnsignedTypes
public fun immutableULongArrayOf(vararg elements: ULong): ImmutableULongArray {
    return ImmutableULongArray(elements)
}

/** Returns a new immutable array which is a copy of this array. */
@ExperimentalUnsignedTypes
public fun ULongArray.toImmutableArray(): ImmutableULongArray {
    return ImmutableULongArray(copyOf())
}

/**
 * Returns a new immutable array which is a copy of the specified range of this array.
 *
 * @param fromIndex the start of the range (inclusive) to copy.
 * @param toIndex the end of the range (exclusive) to copy.
 * @throws IndexOutOfBoundsException if [fromIndex] is less than zero or [toIndex] is greater than
 *   the size of this array.
 * @throws IllegalArgumentException if [fromIndex] is greater than [toIndex].
 */
@ExperimentalUnsignedTypes
public fun ULongArray.toImmutableArray(fromIndex: Int, toIndex: Int): ImmutableULongArray {
    return ImmutableULongArray(copyOfRange(fromIndex, toIndex))
}

@ExperimentalUnsignedTypes
/** Returns an immutable list that wraps this array. */
public fun ImmutableULongArray.asList(): List<ULong> {
    return data.asList()
}

/**
 * Returns a new immutable array which is a copy of the specified range of this array.
 *
 * @param fromIndex the start of the range (inclusive) to copy.
 * @param toIndex the end of the range (exclusive) to copy.
 * @throws IndexOutOfBoundsException if [fromIndex] is less than zero or [toIndex] is greater than
 *   the size of this array.
 * @throws IllegalArgumentException if [fromIndex] is greater than [toIndex].
 */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableULongArray {
    return ImmutableULongArray(data.copyOfRange(fromIndex, toIndex))
}

/** Returns a new mutable array which is a copy of this array. */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.toMutableArray(): ULongArray {
    return data.copyOf()
}

/**
 * Returns a new mutable array which is a copy of the specified range of this array.
 *
 * @param fromIndex the start of the range (inclusive) to copy.
 * @param toIndex the end of the range (exclusive) to copy.
 * @throws IndexOutOfBoundsException if [fromIndex] is less than zero or [toIndex] is greater than
 *   the size of this array.
 * @throws IllegalArgumentException if [fromIndex] is greater than [toIndex].
 */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.toMutableArray(fromIndex: Int, toIndex: Int): ULongArray {
    return data.copyOfRange(fromIndex, toIndex)
}

/** Returns the range of valid indices. */
@ExperimentalUnsignedTypes
public val ImmutableULongArray.indices: IntRange
    get() = data.indices

/** Returns the last valid index. */
@ExperimentalUnsignedTypes
public val ImmutableULongArray.lastIndex: Int
    get() = data.lastIndex

/** Returns `true` if this array is empty. */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.isEmpty(): Boolean {
    return data.isEmpty()
}

/** Returns `true` if this array is not empty. */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.isNotEmpty(): Boolean {
    return data.isNotEmpty()
}

/** Checks if the two arrays are equal to each other based on their contents. */
@ExperimentalUnsignedTypes
public infix fun ImmutableULongArray?.contentEquals(other: ImmutableULongArray?): Boolean {
    return this?.data contentEquals other?.data
}

/** Returns a hash code based on the contents of this array. */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray?.contentHashCode(): Int {
    return this?.data.contentHashCode()
}

/** Returns a string representation of the contents of this array. */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray?.contentToString(): String {
    return this?.data.contentToString()
}

/** Returns `true` if the given [element] is found in this array. */
@ExperimentalUnsignedTypes
public operator fun ImmutableULongArray.contains(element: ULong): Boolean {
    return data.contains(element)
}

/**
 * Returns the index of the first occurrence of the given [element], or -1 if this array does not
 * contain the element.
 */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.indexOf(element: ULong): Int {
    return data.indexOf(element)
}

/**
 * Returns the index of the last occurrence of the given [element], or -1 if this array does not
 * contain the element.
 */
@ExperimentalUnsignedTypes
public fun ImmutableULongArray.lastIndexOf(element: ULong): Int {
    return data.lastIndexOf(element)
}
