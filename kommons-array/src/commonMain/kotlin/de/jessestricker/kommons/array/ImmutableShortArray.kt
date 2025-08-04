//
// NOTE: This file is auto-generated and may not be edited manually.
//
package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.Int
import kotlin.Short
import kotlin.ShortArray
import kotlin.String
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.collections.ShortIterator
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of shorts. */
@JvmInline
public value class ImmutableShortArray internal constructor(internal val storage: ShortArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(ShortArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> Short) : this(ShortArray(size, `init`))

    /** Returns the array element at the given [index]. */
    public operator fun `get`(index: Int): Short = storage[index]

    /** Creates a specialized [ShortIterator] for iterating over the elements of the array. */
    public operator fun iterator(): ShortIterator = storage.iterator()
}

public val ImmutableShortArray.indices: IntRange
    get() = storage.indices

public val ImmutableShortArray.lastIndex: Int
    get() = storage.lastIndex

@DelicateKommonsArrayApi
public fun ShortArray.asImmutableArray(): ImmutableShortArray = ImmutableShortArray(this)

@DelicateKommonsArrayApi public fun ImmutableShortArray.asMutableArray(): ShortArray = storage

public fun ShortArray.toImmutableArray(): ImmutableShortArray = ImmutableShortArray(copyOf())

public fun ImmutableShortArray.toMutableArray(): ShortArray = storage.copyOf()

public fun ImmutableShortArray.asList(): List<Short> = storage.asList()

public operator fun ImmutableShortArray.contains(element: Short): Boolean =
    storage.contains(element)

public fun ImmutableShortArray.indexOf(element: Short): Int = storage.indexOf(element)

public fun ImmutableShortArray.lastIndexOf(element: Short): Int = storage.lastIndexOf(element)

public infix fun ImmutableShortArray?.contentEquals(other: ImmutableShortArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

public fun ImmutableShortArray?.contentHashCode(): Int = this?.storage.contentHashCode()

public fun ImmutableShortArray?.contentToString(): String = this?.storage.contentToString()

public fun ImmutableShortArray.copyInto(
    destination: ShortArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): ShortArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

public fun ImmutableShortArray.copyOf(): ImmutableShortArray = ImmutableShortArray(storage.copyOf())

public fun ImmutableShortArray.copyOf(newSize: Int): ImmutableShortArray =
    ImmutableShortArray(storage.copyOf(newSize))

public fun ImmutableShortArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableShortArray =
    ImmutableShortArray(storage.copyOfRange(fromIndex, toIndex))

public fun ImmutableShortArray.isEmpty(): Boolean = storage.isEmpty()

public fun ImmutableShortArray.isNotEmpty(): Boolean = storage.isNotEmpty()

public operator fun ImmutableShortArray.plus(element: Short): ImmutableShortArray =
    ImmutableShortArray(storage.plus(element))

public operator fun ImmutableShortArray.plus(elements: ShortArray): ImmutableShortArray =
    ImmutableShortArray(storage.plus(elements))

public operator fun ImmutableShortArray.plus(elements: ImmutableShortArray): ImmutableShortArray =
    ImmutableShortArray(storage.plus(elements.storage))

public operator fun ImmutableShortArray.plus(elements: Collection<Short>): ImmutableShortArray =
    ImmutableShortArray(storage.plus(elements))

public fun ImmutableShortArray.reversedArray(): ImmutableShortArray =
    ImmutableShortArray(storage.reversedArray())

public fun ImmutableShortArray.sliceArray(indices: Collection<Int>): ImmutableShortArray =
    ImmutableShortArray(storage.sliceArray(indices))

public fun ImmutableShortArray.sliceArray(indices: IntRange): ImmutableShortArray =
    ImmutableShortArray(storage.sliceArray(indices))

public fun ImmutableShortArray.sortedArray(): ImmutableShortArray =
    ImmutableShortArray(storage.sortedArray())

public fun ImmutableShortArray.sortedArrayDescending(): ImmutableShortArray =
    ImmutableShortArray(storage.sortedArrayDescending())
