//
// NOTE: This file is auto-generated and may not be edited manually.
//
@file:Suppress("detekt:TooManyFunctions")

package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.ExperimentalUnsignedTypes
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.ULong
import kotlin.ULongArray
import kotlin.collections.Collection
import kotlin.collections.Iterator
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of unsigned longs. */
@ExperimentalUnsignedTypes
@JvmInline
public value class ImmutableULongArray internal constructor(internal val storage: ULongArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(ULongArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> ULong) : this(ULongArray(size, `init`))

    /** Returns the array element at the given [index]. */
    @Suppress("detekt:FunctionNaming") public operator fun `get`(index: Int): ULong = storage[index]

    /** Creates a specialized [Iterator<ULong>] for iterating over the elements of the array. */
    public operator fun iterator(): Iterator<ULong> = storage.iterator()
}

@ExperimentalUnsignedTypes
public val ImmutableULongArray.indices: IntRange
    get() = storage.indices

@ExperimentalUnsignedTypes
public val ImmutableULongArray.lastIndex: Int
    get() = storage.lastIndex

@ExperimentalUnsignedTypes
@DelicateKommonsArrayApi
public fun ULongArray.asImmutableArray(): ImmutableULongArray = ImmutableULongArray(this)

@ExperimentalUnsignedTypes
@DelicateKommonsArrayApi
public fun ImmutableULongArray.asMutableArray(): ULongArray = storage

@ExperimentalUnsignedTypes
public fun ULongArray.toImmutableArray(): ImmutableULongArray = ImmutableULongArray(copyOf())

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.toMutableArray(): ULongArray = storage.copyOf()

@ExperimentalUnsignedTypes public fun ImmutableULongArray.asList(): List<ULong> = storage.asList()

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.asImmutableLongArray(): ImmutableLongArray =
    ImmutableLongArray(storage.asLongArray())

@ExperimentalUnsignedTypes
public operator fun ImmutableULongArray.contains(element: ULong): Boolean =
    storage.contains(element)

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.indexOf(element: ULong): Int = storage.indexOf(element)

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.lastIndexOf(element: ULong): Int = storage.lastIndexOf(element)

@ExperimentalUnsignedTypes
public infix fun ImmutableULongArray?.contentEquals(other: ImmutableULongArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

@ExperimentalUnsignedTypes
public fun ImmutableULongArray?.contentHashCode(): Int = this?.storage.contentHashCode()

@ExperimentalUnsignedTypes
public fun ImmutableULongArray?.contentToString(): String = this?.storage.contentToString()

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.copyInto(
    destination: ULongArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): ULongArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.copyOf(): ImmutableULongArray = ImmutableULongArray(storage.copyOf())

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.copyOf(newSize: Int): ImmutableULongArray =
    ImmutableULongArray(storage.copyOf(newSize))

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableULongArray =
    ImmutableULongArray(storage.copyOfRange(fromIndex, toIndex))

@ExperimentalUnsignedTypes public fun ImmutableULongArray.isEmpty(): Boolean = storage.isEmpty()

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.isNotEmpty(): Boolean = storage.isNotEmpty()

@ExperimentalUnsignedTypes
public operator fun ImmutableULongArray.plus(element: ULong): ImmutableULongArray =
    ImmutableULongArray(storage.plus(element))

@ExperimentalUnsignedTypes
public operator fun ImmutableULongArray.plus(elements: ULongArray): ImmutableULongArray =
    ImmutableULongArray(storage.plus(elements))

@ExperimentalUnsignedTypes
public operator fun ImmutableULongArray.plus(elements: ImmutableULongArray): ImmutableULongArray =
    ImmutableULongArray(storage.plus(elements.storage))

@ExperimentalUnsignedTypes
public operator fun ImmutableULongArray.plus(elements: Collection<ULong>): ImmutableULongArray =
    ImmutableULongArray(storage.plus(elements))

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.reversedArray(): ImmutableULongArray =
    ImmutableULongArray(storage.reversedArray())

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.sliceArray(indices: Collection<Int>): ImmutableULongArray =
    ImmutableULongArray(storage.sliceArray(indices))

@ExperimentalUnsignedTypes
public fun ImmutableULongArray.sliceArray(indices: IntRange): ImmutableULongArray =
    ImmutableULongArray(storage.sliceArray(indices))
