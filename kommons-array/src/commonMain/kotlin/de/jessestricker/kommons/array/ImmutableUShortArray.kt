//
// NOTE: This file is auto-generated and may not be edited manually.
//
package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.ExperimentalUnsignedTypes
import kotlin.Int
import kotlin.String
import kotlin.UShort
import kotlin.UShortArray
import kotlin.collections.Collection
import kotlin.collections.Iterator
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of unsigned shorts. */
@ExperimentalUnsignedTypes
@JvmInline
public value class ImmutableUShortArray internal constructor(internal val storage: UShortArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(UShortArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> UShort) : this(UShortArray(size, `init`))

    /** Returns the array element at the given [index]. */
    public operator fun `get`(index: Int): UShort = storage[index]

    /** Creates a specialized [Iterator<UShort>] for iterating over the elements of the array. */
    public operator fun iterator(): Iterator<UShort> = storage.iterator()
}

@ExperimentalUnsignedTypes
public val ImmutableUShortArray.indices: IntRange
    get() = storage.indices

@ExperimentalUnsignedTypes
public val ImmutableUShortArray.lastIndex: Int
    get() = storage.lastIndex

@ExperimentalUnsignedTypes
@DelicateKommonsArrayApi
public fun UShortArray.asImmutableArray(): ImmutableUShortArray = ImmutableUShortArray(this)

@ExperimentalUnsignedTypes
@DelicateKommonsArrayApi
public fun ImmutableUShortArray.asMutableArray(): UShortArray = storage

@ExperimentalUnsignedTypes
public fun UShortArray.toImmutableArray(): ImmutableUShortArray = ImmutableUShortArray(copyOf())

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.toMutableArray(): UShortArray = storage.copyOf()

@ExperimentalUnsignedTypes public fun ImmutableUShortArray.asList(): List<UShort> = storage.asList()

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.asImmutableShortArray(): ImmutableShortArray =
    ImmutableShortArray(storage.asShortArray())

@ExperimentalUnsignedTypes
public operator fun ImmutableUShortArray.contains(element: UShort): Boolean =
    storage.contains(element)

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.indexOf(element: UShort): Int = storage.indexOf(element)

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.lastIndexOf(element: UShort): Int = storage.lastIndexOf(element)

@ExperimentalUnsignedTypes
public infix fun ImmutableUShortArray?.contentEquals(other: ImmutableUShortArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray?.contentHashCode(): Int = this?.storage.contentHashCode()

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray?.contentToString(): String = this?.storage.contentToString()

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.copyInto(
    destination: UShortArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): UShortArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.copyOf(): ImmutableUShortArray =
    ImmutableUShortArray(storage.copyOf())

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.copyOf(newSize: Int): ImmutableUShortArray =
    ImmutableUShortArray(storage.copyOf(newSize))

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableUShortArray =
    ImmutableUShortArray(storage.copyOfRange(fromIndex, toIndex))

@ExperimentalUnsignedTypes public fun ImmutableUShortArray.isEmpty(): Boolean = storage.isEmpty()

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.isNotEmpty(): Boolean = storage.isNotEmpty()

@ExperimentalUnsignedTypes
public operator fun ImmutableUShortArray.plus(element: UShort): ImmutableUShortArray =
    ImmutableUShortArray(storage.plus(element))

@ExperimentalUnsignedTypes
public operator fun ImmutableUShortArray.plus(elements: UShortArray): ImmutableUShortArray =
    ImmutableUShortArray(storage.plus(elements))

@ExperimentalUnsignedTypes
public operator fun ImmutableUShortArray.plus(
    elements: ImmutableUShortArray
): ImmutableUShortArray = ImmutableUShortArray(storage.plus(elements.storage))

@ExperimentalUnsignedTypes
public operator fun ImmutableUShortArray.plus(elements: Collection<UShort>): ImmutableUShortArray =
    ImmutableUShortArray(storage.plus(elements))

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.reversedArray(): ImmutableUShortArray =
    ImmutableUShortArray(storage.reversedArray())

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.sliceArray(indices: Collection<Int>): ImmutableUShortArray =
    ImmutableUShortArray(storage.sliceArray(indices))

@ExperimentalUnsignedTypes
public fun ImmutableUShortArray.sliceArray(indices: IntRange): ImmutableUShortArray =
    ImmutableUShortArray(storage.sliceArray(indices))
