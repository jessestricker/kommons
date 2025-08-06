//
// NOTE: This file is auto-generated and may not be edited manually.
//
@file:Suppress("detekt:TooManyFunctions")

package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.Float
import kotlin.FloatArray
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.Collection
import kotlin.collections.FloatIterator
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of floats. */
@JvmInline
public value class ImmutableFloatArray internal constructor(internal val storage: FloatArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(FloatArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> Float) : this(FloatArray(size, `init`))

    /** Returns the array element at the given [index]. */
    @Suppress("detekt:FunctionNaming") public operator fun `get`(index: Int): Float = storage[index]

    /** Creates a specialized [FloatIterator] for iterating over the elements of the array. */
    public operator fun iterator(): FloatIterator = storage.iterator()
}

public val ImmutableFloatArray.indices: IntRange
    get() = storage.indices

public val ImmutableFloatArray.lastIndex: Int
    get() = storage.lastIndex

@DelicateKommonsArrayApi
public fun FloatArray.asImmutableArray(): ImmutableFloatArray = ImmutableFloatArray(this)

@DelicateKommonsArrayApi public fun ImmutableFloatArray.asMutableArray(): FloatArray = storage

public fun FloatArray.toImmutableArray(): ImmutableFloatArray = ImmutableFloatArray(copyOf())

public fun ImmutableFloatArray.toMutableArray(): FloatArray = storage.copyOf()

public fun ImmutableFloatArray.asList(): List<Float> = storage.asList()

public infix fun ImmutableFloatArray?.contentEquals(other: ImmutableFloatArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

public fun ImmutableFloatArray?.contentHashCode(): Int = this?.storage.contentHashCode()

public fun ImmutableFloatArray?.contentToString(): String = this?.storage.contentToString()

public fun ImmutableFloatArray.copyInto(
    destination: FloatArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): FloatArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

public fun ImmutableFloatArray.copyOf(): ImmutableFloatArray = ImmutableFloatArray(storage.copyOf())

public fun ImmutableFloatArray.copyOf(newSize: Int): ImmutableFloatArray =
    ImmutableFloatArray(storage.copyOf(newSize))

public fun ImmutableFloatArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableFloatArray =
    ImmutableFloatArray(storage.copyOfRange(fromIndex, toIndex))

public fun ImmutableFloatArray.isEmpty(): Boolean = storage.isEmpty()

public fun ImmutableFloatArray.isNotEmpty(): Boolean = storage.isNotEmpty()

public operator fun ImmutableFloatArray.plus(element: Float): ImmutableFloatArray =
    ImmutableFloatArray(storage.plus(element))

public operator fun ImmutableFloatArray.plus(elements: FloatArray): ImmutableFloatArray =
    ImmutableFloatArray(storage.plus(elements))

public operator fun ImmutableFloatArray.plus(elements: ImmutableFloatArray): ImmutableFloatArray =
    ImmutableFloatArray(storage.plus(elements.storage))

public operator fun ImmutableFloatArray.plus(elements: Collection<Float>): ImmutableFloatArray =
    ImmutableFloatArray(storage.plus(elements))

public fun ImmutableFloatArray.reversedArray(): ImmutableFloatArray =
    ImmutableFloatArray(storage.reversedArray())

public fun ImmutableFloatArray.sliceArray(indices: Collection<Int>): ImmutableFloatArray =
    ImmutableFloatArray(storage.sliceArray(indices))

public fun ImmutableFloatArray.sliceArray(indices: IntRange): ImmutableFloatArray =
    ImmutableFloatArray(storage.sliceArray(indices))

public fun ImmutableFloatArray.sortedArray(): ImmutableFloatArray =
    ImmutableFloatArray(storage.sortedArray())

public fun ImmutableFloatArray.sortedArrayDescending(): ImmutableFloatArray =
    ImmutableFloatArray(storage.sortedArrayDescending())
