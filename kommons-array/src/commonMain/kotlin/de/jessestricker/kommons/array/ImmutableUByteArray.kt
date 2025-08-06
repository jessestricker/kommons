//
// NOTE: This file is auto-generated and may not be edited manually.
//
package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.ExperimentalUnsignedTypes
import kotlin.Int
import kotlin.String
import kotlin.UByte
import kotlin.UByteArray
import kotlin.collections.Collection
import kotlin.collections.Iterator
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of unsigned bytes. */
@ExperimentalUnsignedTypes
@JvmInline
public value class ImmutableUByteArray internal constructor(internal val storage: UByteArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(UByteArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> UByte) : this(UByteArray(size, `init`))

    /** Returns the array element at the given [index]. */
    public operator fun `get`(index: Int): UByte = storage[index]

    /** Creates a specialized [Iterator<UByte>] for iterating over the elements of the array. */
    public operator fun iterator(): Iterator<UByte> = storage.iterator()
}

@ExperimentalUnsignedTypes
public val ImmutableUByteArray.indices: IntRange
    get() = storage.indices

@ExperimentalUnsignedTypes
public val ImmutableUByteArray.lastIndex: Int
    get() = storage.lastIndex

@ExperimentalUnsignedTypes
@DelicateKommonsArrayApi
public fun UByteArray.asImmutableArray(): ImmutableUByteArray = ImmutableUByteArray(this)

@ExperimentalUnsignedTypes
@DelicateKommonsArrayApi
public fun ImmutableUByteArray.asMutableArray(): UByteArray = storage

@ExperimentalUnsignedTypes
public fun UByteArray.toImmutableArray(): ImmutableUByteArray = ImmutableUByteArray(copyOf())

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.toMutableArray(): UByteArray = storage.copyOf()

@ExperimentalUnsignedTypes public fun ImmutableUByteArray.asList(): List<UByte> = storage.asList()

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.asImmutableByteArray(): ImmutableByteArray =
    ImmutableByteArray(storage.asByteArray())

@ExperimentalUnsignedTypes
public operator fun ImmutableUByteArray.contains(element: UByte): Boolean =
    storage.contains(element)

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.indexOf(element: UByte): Int = storage.indexOf(element)

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.lastIndexOf(element: UByte): Int = storage.lastIndexOf(element)

@ExperimentalUnsignedTypes
public infix fun ImmutableUByteArray?.contentEquals(other: ImmutableUByteArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray?.contentHashCode(): Int = this?.storage.contentHashCode()

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray?.contentToString(): String = this?.storage.contentToString()

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.copyInto(
    destination: UByteArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): UByteArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.copyOf(): ImmutableUByteArray = ImmutableUByteArray(storage.copyOf())

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.copyOf(newSize: Int): ImmutableUByteArray =
    ImmutableUByteArray(storage.copyOf(newSize))

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableUByteArray =
    ImmutableUByteArray(storage.copyOfRange(fromIndex, toIndex))

@ExperimentalUnsignedTypes public fun ImmutableUByteArray.isEmpty(): Boolean = storage.isEmpty()

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.isNotEmpty(): Boolean = storage.isNotEmpty()

@ExperimentalUnsignedTypes
public operator fun ImmutableUByteArray.plus(element: UByte): ImmutableUByteArray =
    ImmutableUByteArray(storage.plus(element))

@ExperimentalUnsignedTypes
public operator fun ImmutableUByteArray.plus(elements: UByteArray): ImmutableUByteArray =
    ImmutableUByteArray(storage.plus(elements))

@ExperimentalUnsignedTypes
public operator fun ImmutableUByteArray.plus(elements: ImmutableUByteArray): ImmutableUByteArray =
    ImmutableUByteArray(storage.plus(elements.storage))

@ExperimentalUnsignedTypes
public operator fun ImmutableUByteArray.plus(elements: Collection<UByte>): ImmutableUByteArray =
    ImmutableUByteArray(storage.plus(elements))

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.reversedArray(): ImmutableUByteArray =
    ImmutableUByteArray(storage.reversedArray())

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.sliceArray(indices: Collection<Int>): ImmutableUByteArray =
    ImmutableUByteArray(storage.sliceArray(indices))

@ExperimentalUnsignedTypes
public fun ImmutableUByteArray.sliceArray(indices: IntRange): ImmutableUByteArray =
    ImmutableUByteArray(storage.sliceArray(indices))
