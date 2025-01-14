/*
 * This file is auto-generated, do not edit!
 */

@file:Suppress("TooManyFunctions")

package kommons.arrays

/**
 * An immutable array of unsigned ints.
 *
 * When targeting the JVM, instances of this class are represented as `int[]`.
 *
 * @see UIntArray
 */
@ExperimentalUnsignedTypes
@JvmInline
public value class ImmutableUIntArray internal constructor(internal val data: UIntArray) {
    /**
     * Creates a new immutable array of the specified [size], with all elements initialized to zero.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public constructor(size: Int) : this(UIntArray(size))

    /**
     * Creates a new immutable array of the specified [size], where each element is calculated by
     * calling the specified [init] function.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public constructor(size: Int, init: (index: Int) -> UInt) : this(UIntArray(size, init))

    /** The number of elements. */
    public val size: Int
        get() = data.size

    /**
     * Returns the element at the given [index].
     *
     * @throws IndexOutOfBoundsException if the [index] is out of bounds.
     */
    public operator fun get(index: Int): UInt {
        return data[index]
    }

    /**
     * Creates a specialized iterator for iterating over the elements.
     *
     * @see Iterator<UInt>
     */
    public operator fun iterator(): Iterator<UInt> {
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
        public fun wrap(mutableArray: UIntArray): ImmutableUIntArray {
            return ImmutableUIntArray(mutableArray)
        }
    }
}

/** Returns a new immutable array which contains the given [elements]. */
@ExperimentalUnsignedTypes
public fun immutableUIntArrayOf(vararg elements: UInt): ImmutableUIntArray {
    return ImmutableUIntArray(elements)
}

/** Returns a new immutable array which is a copy of this array. */
@ExperimentalUnsignedTypes
public fun UIntArray.toImmutableArray(): ImmutableUIntArray {
    return ImmutableUIntArray(copyOf())
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
public fun UIntArray.toImmutableArray(fromIndex: Int, toIndex: Int): ImmutableUIntArray {
    return ImmutableUIntArray(copyOfRange(fromIndex, toIndex))
}

@ExperimentalUnsignedTypes
/** Returns an immutable list that wraps this array. */
public fun ImmutableUIntArray.asList(): List<UInt> {
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
public fun ImmutableUIntArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableUIntArray {
    return ImmutableUIntArray(data.copyOfRange(fromIndex, toIndex))
}

/** Returns a new mutable array which is a copy of this array. */
@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.toMutableArray(): UIntArray {
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
public fun ImmutableUIntArray.toMutableArray(fromIndex: Int, toIndex: Int): UIntArray {
    return data.copyOfRange(fromIndex, toIndex)
}

/** Returns the range of valid indices. */
@ExperimentalUnsignedTypes
public val ImmutableUIntArray.indices: IntRange
    get() = data.indices

/** Returns the last valid index. */
@ExperimentalUnsignedTypes
public val ImmutableUIntArray.lastIndex: Int
    get() = data.lastIndex

/** Returns `true` if this array is empty. */
@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.isEmpty(): Boolean {
    return data.isEmpty()
}

/** Returns `true` if this array is not empty. */
@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.isNotEmpty(): Boolean {
    return data.isNotEmpty()
}

/** Checks if the two arrays are equal to each other based on their contents. */
@ExperimentalUnsignedTypes
public infix fun ImmutableUIntArray?.contentEquals(other: ImmutableUIntArray?): Boolean {
    return this?.data contentEquals other?.data
}

/** Returns a hash code based on the contents of this array. */
@ExperimentalUnsignedTypes
public fun ImmutableUIntArray?.contentHashCode(): Int {
    return this?.data.contentHashCode()
}

/** Returns a string representation of the contents of this array. */
@ExperimentalUnsignedTypes
public fun ImmutableUIntArray?.contentToString(): String {
    return this?.data.contentToString()
}

/** Returns `true` if the given [element] is found in this array. */
@ExperimentalUnsignedTypes
public operator fun ImmutableUIntArray.contains(element: UInt): Boolean {
    return data.contains(element)
}

/**
 * Returns the index of the first occurrence of the given [element], or -1 if this array does not
 * contain the element.
 */
@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.indexOf(element: UInt): Int {
    return data.indexOf(element)
}

/**
 * Returns the index of the last occurrence of the given [element], or -1 if this array does not
 * contain the element.
 */
@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.lastIndexOf(element: UInt): Int {
    return data.lastIndexOf(element)
}