//
// NOTE: This file is auto-generated and may not be edited manually.
//
package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.ExperimentalUnsignedTypes
import kotlin.Int
import kotlin.String
import kotlin.UInt
import kotlin.UIntArray
import kotlin.collections.Collection
import kotlin.collections.Iterator
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of unsigned ints. */
@ExperimentalUnsignedTypes
@JvmInline
public value class ImmutableUIntArray internal constructor(internal val storage: UIntArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(UIntArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> UInt) : this(UIntArray(size, `init`))

    /** Returns the array element at the given [index]. */
    public operator fun `get`(index: Int): UInt = storage[index]

    /** Creates a specialized [Iterator<UInt>] for iterating over the elements of the array. */
    public operator fun iterator(): Iterator<UInt> = storage.iterator()
}

@ExperimentalUnsignedTypes
public val ImmutableUIntArray.indices: IntRange
    get() = storage.indices

@ExperimentalUnsignedTypes
public val ImmutableUIntArray.lastIndex: Int
    get() = storage.lastIndex

@ExperimentalUnsignedTypes
@DelicateKommonsArrayApi
public fun UIntArray.asImmutableArray(): ImmutableUIntArray = ImmutableUIntArray(this)

@ExperimentalUnsignedTypes
@DelicateKommonsArrayApi
public fun ImmutableUIntArray.asMutableArray(): UIntArray = storage

@ExperimentalUnsignedTypes
public fun UIntArray.toImmutableArray(): ImmutableUIntArray = ImmutableUIntArray(copyOf())

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.toMutableArray(): UIntArray = storage.copyOf()

@ExperimentalUnsignedTypes public fun ImmutableUIntArray.asList(): List<UInt> = storage.asList()

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.asImmutableIntArray(): ImmutableIntArray =
    ImmutableIntArray(storage.asIntArray())

@ExperimentalUnsignedTypes
public operator fun ImmutableUIntArray.contains(element: UInt): Boolean = storage.contains(element)

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.indexOf(element: UInt): Int = storage.indexOf(element)

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.lastIndexOf(element: UInt): Int = storage.lastIndexOf(element)

@ExperimentalUnsignedTypes
public infix fun ImmutableUIntArray?.contentEquals(other: ImmutableUIntArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray?.contentHashCode(): Int = this?.storage.contentHashCode()

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray?.contentToString(): String = this?.storage.contentToString()

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.copyInto(
    destination: UIntArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): UIntArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.copyOf(): ImmutableUIntArray = ImmutableUIntArray(storage.copyOf())

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.copyOf(newSize: Int): ImmutableUIntArray =
    ImmutableUIntArray(storage.copyOf(newSize))

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableUIntArray =
    ImmutableUIntArray(storage.copyOfRange(fromIndex, toIndex))

@ExperimentalUnsignedTypes public fun ImmutableUIntArray.isEmpty(): Boolean = storage.isEmpty()

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.isNotEmpty(): Boolean = storage.isNotEmpty()

@ExperimentalUnsignedTypes
public operator fun ImmutableUIntArray.plus(element: UInt): ImmutableUIntArray =
    ImmutableUIntArray(storage.plus(element))

@ExperimentalUnsignedTypes
public operator fun ImmutableUIntArray.plus(elements: UIntArray): ImmutableUIntArray =
    ImmutableUIntArray(storage.plus(elements))

@ExperimentalUnsignedTypes
public operator fun ImmutableUIntArray.plus(elements: ImmutableUIntArray): ImmutableUIntArray =
    ImmutableUIntArray(storage.plus(elements.storage))

@ExperimentalUnsignedTypes
public operator fun ImmutableUIntArray.plus(elements: Collection<UInt>): ImmutableUIntArray =
    ImmutableUIntArray(storage.plus(elements))

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.reversedArray(): ImmutableUIntArray =
    ImmutableUIntArray(storage.reversedArray())

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.sliceArray(indices: Collection<Int>): ImmutableUIntArray =
    ImmutableUIntArray(storage.sliceArray(indices))

@ExperimentalUnsignedTypes
public fun ImmutableUIntArray.sliceArray(indices: IntRange): ImmutableUIntArray =
    ImmutableUIntArray(storage.sliceArray(indices))
