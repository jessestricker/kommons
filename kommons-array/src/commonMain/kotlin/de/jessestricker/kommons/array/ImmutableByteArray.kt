//
// NOTE: This file is auto-generated and may not be edited manually.
//
@file:Suppress("detekt:TooManyFunctions")

package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.Byte
import kotlin.ByteArray
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.ByteIterator
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of bytes. */
@JvmInline
public value class ImmutableByteArray internal constructor(internal val storage: ByteArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(ByteArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> Byte) : this(ByteArray(size, `init`))

    /** Returns the array element at the given [index]. */
    @Suppress("detekt:FunctionNaming") public operator fun `get`(index: Int): Byte = storage[index]

    /** Creates a specialized [ByteIterator] for iterating over the elements of the array. */
    public operator fun iterator(): ByteIterator = storage.iterator()
}

public val ImmutableByteArray.indices: IntRange
    get() = storage.indices

public val ImmutableByteArray.lastIndex: Int
    get() = storage.lastIndex

@DelicateKommonsArrayApi
public fun ByteArray.asImmutableArray(): ImmutableByteArray = ImmutableByteArray(this)

@DelicateKommonsArrayApi public fun ImmutableByteArray.asMutableArray(): ByteArray = storage

public fun ByteArray.toImmutableArray(): ImmutableByteArray = ImmutableByteArray(copyOf())

public fun ImmutableByteArray.toMutableArray(): ByteArray = storage.copyOf()

public fun ImmutableByteArray.asList(): List<Byte> = storage.asList()

public operator fun ImmutableByteArray.contains(element: Byte): Boolean = storage.contains(element)

public fun ImmutableByteArray.indexOf(element: Byte): Int = storage.indexOf(element)

public fun ImmutableByteArray.lastIndexOf(element: Byte): Int = storage.lastIndexOf(element)

public infix fun ImmutableByteArray?.contentEquals(other: ImmutableByteArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

public fun ImmutableByteArray?.contentHashCode(): Int = this?.storage.contentHashCode()

public fun ImmutableByteArray?.contentToString(): String = this?.storage.contentToString()

public fun ImmutableByteArray.copyInto(
    destination: ByteArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): ByteArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

public fun ImmutableByteArray.copyOf(): ImmutableByteArray = ImmutableByteArray(storage.copyOf())

public fun ImmutableByteArray.copyOf(newSize: Int): ImmutableByteArray =
    ImmutableByteArray(storage.copyOf(newSize))

public fun ImmutableByteArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableByteArray =
    ImmutableByteArray(storage.copyOfRange(fromIndex, toIndex))

public fun ImmutableByteArray.isEmpty(): Boolean = storage.isEmpty()

public fun ImmutableByteArray.isNotEmpty(): Boolean = storage.isNotEmpty()

public operator fun ImmutableByteArray.plus(element: Byte): ImmutableByteArray =
    ImmutableByteArray(storage.plus(element))

public operator fun ImmutableByteArray.plus(elements: ByteArray): ImmutableByteArray =
    ImmutableByteArray(storage.plus(elements))

public operator fun ImmutableByteArray.plus(elements: ImmutableByteArray): ImmutableByteArray =
    ImmutableByteArray(storage.plus(elements.storage))

public operator fun ImmutableByteArray.plus(elements: Collection<Byte>): ImmutableByteArray =
    ImmutableByteArray(storage.plus(elements))

public fun ImmutableByteArray.reversedArray(): ImmutableByteArray =
    ImmutableByteArray(storage.reversedArray())

public fun ImmutableByteArray.sliceArray(indices: Collection<Int>): ImmutableByteArray =
    ImmutableByteArray(storage.sliceArray(indices))

public fun ImmutableByteArray.sliceArray(indices: IntRange): ImmutableByteArray =
    ImmutableByteArray(storage.sliceArray(indices))

public fun ImmutableByteArray.sortedArray(): ImmutableByteArray =
    ImmutableByteArray(storage.sortedArray())

public fun ImmutableByteArray.sortedArrayDescending(): ImmutableByteArray =
    ImmutableByteArray(storage.sortedArrayDescending())
