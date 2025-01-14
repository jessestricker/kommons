/*
 * This file is auto-generated, do not edit!
 */

@file:Suppress("TooManyFunctions")

package kommons.arrays

/**
 * An immutable array of doubles.
 *
 * When targeting the JVM, instances of this class are represented as `double[]`.
 *
 * @see DoubleArray
 */
@JvmInline
public value class ImmutableDoubleArray internal constructor(internal val data: DoubleArray) {
    /**
     * Creates a new immutable array of the specified [size], with all elements initialized to zero.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public constructor(size: Int) : this(DoubleArray(size))

    /**
     * Creates a new immutable array of the specified [size], where each element is calculated by
     * calling the specified [init] function.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public constructor(size: Int, init: (index: Int) -> Double) : this(DoubleArray(size, init))

    /** The number of elements. */
    public val size: Int
        get() = data.size

    /**
     * Returns the element at the given [index].
     *
     * @throws IndexOutOfBoundsException if the [index] is out of bounds.
     */
    public operator fun get(index: Int): Double {
        return data[index]
    }

    /**
     * Creates a specialized iterator for iterating over the elements.
     *
     * @see DoubleIterator
     */
    public operator fun iterator(): DoubleIterator {
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
        public fun wrap(mutableArray: DoubleArray): ImmutableDoubleArray {
            return ImmutableDoubleArray(mutableArray)
        }
    }
}

/** Returns a new immutable array which contains the given [elements]. */
public fun immutableDoubleArrayOf(vararg elements: Double): ImmutableDoubleArray {
    return ImmutableDoubleArray(elements)
}

/** Returns a new immutable array which is a copy of this array. */
public fun DoubleArray.toImmutableArray(): ImmutableDoubleArray {
    return ImmutableDoubleArray(copyOf())
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
public fun DoubleArray.toImmutableArray(fromIndex: Int, toIndex: Int): ImmutableDoubleArray {
    return ImmutableDoubleArray(copyOfRange(fromIndex, toIndex))
}

/** Returns an immutable list that wraps this array. */
public fun ImmutableDoubleArray.asList(): List<Double> {
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
public fun ImmutableDoubleArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableDoubleArray {
    return ImmutableDoubleArray(data.copyOfRange(fromIndex, toIndex))
}

/** Returns a new mutable array which is a copy of this array. */
public fun ImmutableDoubleArray.toMutableArray(): DoubleArray {
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
public fun ImmutableDoubleArray.toMutableArray(fromIndex: Int, toIndex: Int): DoubleArray {
    return data.copyOfRange(fromIndex, toIndex)
}

/** Returns the range of valid indices. */
public val ImmutableDoubleArray.indices: IntRange
    get() = data.indices

/** Returns the last valid index. */
public val ImmutableDoubleArray.lastIndex: Int
    get() = data.lastIndex

/** Returns `true` if this array is empty. */
public fun ImmutableDoubleArray.isEmpty(): Boolean {
    return data.isEmpty()
}

/** Returns `true` if this array is not empty. */
public fun ImmutableDoubleArray.isNotEmpty(): Boolean {
    return data.isNotEmpty()
}

/** Checks if the two arrays are equal to each other based on their contents. */
public infix fun ImmutableDoubleArray?.contentEquals(other: ImmutableDoubleArray?): Boolean {
    return this?.data contentEquals other?.data
}

/** Returns a hash code based on the contents of this array. */
public fun ImmutableDoubleArray?.contentHashCode(): Int {
    return this?.data.contentHashCode()
}

/** Returns a string representation of the contents of this array. */
public fun ImmutableDoubleArray?.contentToString(): String {
    return this?.data.contentToString()
}
