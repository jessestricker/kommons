/*
 * This file is auto-generated, do not edit!
 */

@file:Suppress("TooManyFunctions")

package kommons.arrays

/**
 * An immutable array of floats.
 *
 * When targeting the JVM, instances of this class are represented as `float[]`.
 *
 * @see FloatArray
 */
@JvmInline
public value class ImmutableFloatArray internal constructor(internal val data: FloatArray) {
    /**
     * Creates a new immutable array of the specified [size], with all elements initialized to zero.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public constructor(size: Int) : this(FloatArray(size))

    /**
     * Creates a new immutable array of the specified [size], where each element is calculated by
     * calling the specified [init] function.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public constructor(size: Int, init: (index: Int) -> Float) : this(FloatArray(size, init))

    /** The number of elements. */
    public val size: Int
        get() = data.size

    /**
     * Returns the element at the given [index].
     *
     * @throws IndexOutOfBoundsException if the [index] is out of bounds.
     */
    public operator fun get(index: Int): Float {
        return data[index]
    }

    /**
     * Creates a specialized iterator for iterating over the elements.
     *
     * @see FloatIterator
     */
    public operator fun iterator(): FloatIterator {
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
        public fun wrap(mutableArray: FloatArray): ImmutableFloatArray {
            return ImmutableFloatArray(mutableArray)
        }
    }
}

/** Returns a new immutable array which contains the given [elements]. */
public fun immutableFloatArrayOf(vararg elements: Float): ImmutableFloatArray {
    return ImmutableFloatArray(elements)
}

/** Returns a new immutable array which is a copy of this array. */
public fun FloatArray.toImmutableArray(): ImmutableFloatArray {
    return ImmutableFloatArray(copyOf())
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
public fun FloatArray.toImmutableArray(fromIndex: Int, toIndex: Int): ImmutableFloatArray {
    return ImmutableFloatArray(copyOfRange(fromIndex, toIndex))
}

/** Returns an immutable list that wraps this array. */
public fun ImmutableFloatArray.asList(): List<Float> {
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
public fun ImmutableFloatArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableFloatArray {
    return ImmutableFloatArray(data.copyOfRange(fromIndex, toIndex))
}

/** Returns a new mutable array which is a copy of this array. */
public fun ImmutableFloatArray.toMutableArray(): FloatArray {
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
public fun ImmutableFloatArray.toMutableArray(fromIndex: Int, toIndex: Int): FloatArray {
    return data.copyOfRange(fromIndex, toIndex)
}

/** Returns the range of valid indices. */
public val ImmutableFloatArray.indices: IntRange
    get() = data.indices

/** Returns the last valid index. */
public val ImmutableFloatArray.lastIndex: Int
    get() = data.lastIndex

/** Returns `true` if this array is empty. */
public fun ImmutableFloatArray.isEmpty(): Boolean {
    return data.isEmpty()
}

/** Returns `true` if this array is not empty. */
public fun ImmutableFloatArray.isNotEmpty(): Boolean {
    return data.isNotEmpty()
}

/** Checks if the two arrays are equal to each other based on their contents. */
public infix fun ImmutableFloatArray?.contentEquals(other: ImmutableFloatArray?): Boolean {
    return this?.data contentEquals other?.data
}

/** Returns a hash code based on the contents of this array. */
public fun ImmutableFloatArray?.contentHashCode(): Int {
    return this?.data.contentHashCode()
}

/** Returns a string representation of the contents of this array. */
public fun ImmutableFloatArray?.contentToString(): String {
    return this?.data.contentToString()
}
