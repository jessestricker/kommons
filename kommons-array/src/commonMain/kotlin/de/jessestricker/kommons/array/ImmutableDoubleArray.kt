//
// NOTE: This file is auto-generated and may not be edited manually.
//
@file:Suppress("detekt:TooManyFunctions")

package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.Double
import kotlin.DoubleArray
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.Collection
import kotlin.collections.DoubleIterator
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of doubles. */
@JvmInline
public value class ImmutableDoubleArray internal constructor(internal val storage: DoubleArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(DoubleArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> Double) : this(DoubleArray(size, `init`))

    /** Returns the array element at the given [index]. */
    @Suppress("detekt:FunctionNaming")
    public operator fun `get`(index: Int): Double = storage[index]

    /** Creates a specialized [DoubleIterator] for iterating over the elements of the array. */
    public operator fun iterator(): DoubleIterator = storage.iterator()
}

public val ImmutableDoubleArray.indices: IntRange
    get() = storage.indices

public val ImmutableDoubleArray.lastIndex: Int
    get() = storage.lastIndex

@DelicateKommonsArrayApi
public fun DoubleArray.asImmutableArray(): ImmutableDoubleArray = ImmutableDoubleArray(this)

@DelicateKommonsArrayApi public fun ImmutableDoubleArray.asMutableArray(): DoubleArray = storage

public fun DoubleArray.toImmutableArray(): ImmutableDoubleArray = ImmutableDoubleArray(copyOf())

public fun ImmutableDoubleArray.toMutableArray(): DoubleArray = storage.copyOf()

public fun ImmutableDoubleArray.asList(): List<Double> = storage.asList()

public infix fun ImmutableDoubleArray?.contentEquals(other: ImmutableDoubleArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

public fun ImmutableDoubleArray?.contentHashCode(): Int = this?.storage.contentHashCode()

public fun ImmutableDoubleArray?.contentToString(): String = this?.storage.contentToString()

public fun ImmutableDoubleArray.copyInto(
    destination: DoubleArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): DoubleArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

public fun ImmutableDoubleArray.copyOf(): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.copyOf())

public fun ImmutableDoubleArray.copyOf(newSize: Int): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.copyOf(newSize))

public fun ImmutableDoubleArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.copyOfRange(fromIndex, toIndex))

public fun ImmutableDoubleArray.isEmpty(): Boolean = storage.isEmpty()

public fun ImmutableDoubleArray.isNotEmpty(): Boolean = storage.isNotEmpty()

public operator fun ImmutableDoubleArray.plus(element: Double): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.plus(element))

public operator fun ImmutableDoubleArray.plus(elements: DoubleArray): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.plus(elements))

public operator fun ImmutableDoubleArray.plus(
    elements: ImmutableDoubleArray
): ImmutableDoubleArray = ImmutableDoubleArray(storage.plus(elements.storage))

public operator fun ImmutableDoubleArray.plus(elements: Collection<Double>): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.plus(elements))

public fun ImmutableDoubleArray.reversedArray(): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.reversedArray())

public fun ImmutableDoubleArray.sliceArray(indices: Collection<Int>): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.sliceArray(indices))

public fun ImmutableDoubleArray.sliceArray(indices: IntRange): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.sliceArray(indices))

public fun ImmutableDoubleArray.sortedArray(): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.sortedArray())

public fun ImmutableDoubleArray.sortedArrayDescending(): ImmutableDoubleArray =
    ImmutableDoubleArray(storage.sortedArrayDescending())
