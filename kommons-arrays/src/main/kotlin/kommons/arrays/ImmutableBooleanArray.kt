/*
 * This file is auto-generated, do not edit!
 */

@file:Suppress("TooManyFunctions")

package kommons.arrays

/**
 * An immutable array of booleans.
 *
 * When targeting the JVM, instances of this class are represented as `boolean[]`.
 *
 * @see BooleanArray
 */
@JvmInline
public value class ImmutableBooleanArray internal constructor(internal val data: BooleanArray) {
    /**
     * Creates a new immutable array of the specified [size], with all elements initialized to zero.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public constructor(size: Int) : this(BooleanArray(size))

    /**
     * Creates a new immutable array of the specified [size], where each element is calculated by
     * calling the specified [init] function.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public constructor(size: Int, init: (index: Int) -> Boolean) : this(BooleanArray(size, init))

    /** The number of elements. */
    public val size: Int
        get() = data.size

    /**
     * Returns the element at the given [index].
     *
     * @throws IndexOutOfBoundsException if the [index] is out of bounds.
     */
    public operator fun get(index: Int): Boolean {
        return data[index]
    }

    /**
     * Creates a specialized iterator for iterating over the elements.
     *
     * @see BooleanIterator
     */
    public operator fun iterator(): BooleanIterator {
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
        public fun wrap(mutableArray: BooleanArray): ImmutableBooleanArray {
            return ImmutableBooleanArray(mutableArray)
        }
    }
}

/** Returns a new immutable array which contains the given [elements]. */
public fun immutableBooleanArrayOf(vararg elements: Boolean): ImmutableBooleanArray {
    return ImmutableBooleanArray(elements)
}

/** Returns a new immutable array which is a copy of this array. */
public fun BooleanArray.toImmutableArray(): ImmutableBooleanArray {
    return ImmutableBooleanArray(copyOf())
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
public fun BooleanArray.toImmutableArray(fromIndex: Int, toIndex: Int): ImmutableBooleanArray {
    return ImmutableBooleanArray(copyOfRange(fromIndex, toIndex))
}

/** Returns an immutable list that wraps this array. */
public fun ImmutableBooleanArray.asList(): List<Boolean> {
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
public fun ImmutableBooleanArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableBooleanArray {
    return ImmutableBooleanArray(data.copyOfRange(fromIndex, toIndex))
}

/** Returns a new mutable array which is a copy of this array. */
public fun ImmutableBooleanArray.toMutableArray(): BooleanArray {
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
public fun ImmutableBooleanArray.toMutableArray(fromIndex: Int, toIndex: Int): BooleanArray {
    return data.copyOfRange(fromIndex, toIndex)
}

/** Returns the range of valid indices. */
public val ImmutableBooleanArray.indices: IntRange
    get() = data.indices

/** Returns the last valid index. */
public val ImmutableBooleanArray.lastIndex: Int
    get() = data.lastIndex

/** Returns `true` if this array is empty. */
public fun ImmutableBooleanArray.isEmpty(): Boolean {
    return data.isEmpty()
}

/** Returns `true` if this array is not empty. */
public fun ImmutableBooleanArray.isNotEmpty(): Boolean {
    return data.isNotEmpty()
}

/** Checks if the two arrays are equal to each other based on their contents. */
public infix fun ImmutableBooleanArray?.contentEquals(other: ImmutableBooleanArray?): Boolean {
    return this?.data contentEquals other?.data
}

/** Returns a hash code based on the contents of this array. */
public fun ImmutableBooleanArray?.contentHashCode(): Int {
    return this?.data.contentHashCode()
}

/** Returns a string representation of the contents of this array. */
public fun ImmutableBooleanArray?.contentToString(): String {
    return this?.data.contentToString()
}

/** Returns `true` if the given [element] is found in this array. */
public operator fun ImmutableBooleanArray.contains(element: Boolean): Boolean {
    return data.contains(element)
}

/**
 * Returns the index of the first occurrence of the given [element], or -1 if this array does not
 * contain the element.
 */
public fun ImmutableBooleanArray.indexOf(element: Boolean): Int {
    return data.indexOf(element)
}

/**
 * Returns the index of the last occurrence of the given [element], or -1 if this array does not
 * contain the element.
 */
public fun ImmutableBooleanArray.lastIndexOf(element: Boolean): Int {
    return data.lastIndexOf(element)
}
