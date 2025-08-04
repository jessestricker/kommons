//
// NOTE: This file is auto-generated and may not be edited manually.
//
package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.LongArray
import kotlin.String
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.collections.LongIterator
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of longs. */
@JvmInline
public value class ImmutableLongArray internal constructor(internal val storage: LongArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(LongArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> Long) : this(LongArray(size, `init`))

    /** Returns the array element at the given [index]. */
    public operator fun `get`(index: Int): Long = storage[index]

    /** Creates a specialized [LongIterator] for iterating over the elements of the array. */
    public operator fun iterator(): LongIterator = storage.iterator()
}

public val ImmutableLongArray.indices: IntRange
    get() = storage.indices

public val ImmutableLongArray.lastIndex: Int
    get() = storage.lastIndex

@DelicateKommonsArrayApi
public fun LongArray.asImmutableArray(): ImmutableLongArray = ImmutableLongArray(this)

@DelicateKommonsArrayApi public fun ImmutableLongArray.asMutableArray(): LongArray = storage

public fun LongArray.toImmutableArray(): ImmutableLongArray = ImmutableLongArray(copyOf())

public fun ImmutableLongArray.toMutableArray(): LongArray = storage.copyOf()

public fun ImmutableLongArray.asList(): List<Long> = storage.asList()

public operator fun ImmutableLongArray.contains(element: Long): Boolean = storage.contains(element)

public fun ImmutableLongArray.indexOf(element: Long): Int = storage.indexOf(element)

public fun ImmutableLongArray.lastIndexOf(element: Long): Int = storage.lastIndexOf(element)

public infix fun ImmutableLongArray?.contentEquals(other: ImmutableLongArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

public fun ImmutableLongArray?.contentHashCode(): Int = this?.storage.contentHashCode()

public fun ImmutableLongArray?.contentToString(): String = this?.storage.contentToString()

public fun ImmutableLongArray.copyInto(
    destination: LongArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): LongArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

public fun ImmutableLongArray.copyOf(): ImmutableLongArray = ImmutableLongArray(storage.copyOf())

public fun ImmutableLongArray.copyOf(newSize: Int): ImmutableLongArray =
    ImmutableLongArray(storage.copyOf(newSize))

public fun ImmutableLongArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableLongArray =
    ImmutableLongArray(storage.copyOfRange(fromIndex, toIndex))

public fun ImmutableLongArray.isEmpty(): Boolean = storage.isEmpty()

public fun ImmutableLongArray.isNotEmpty(): Boolean = storage.isNotEmpty()

public operator fun ImmutableLongArray.plus(element: Long): ImmutableLongArray =
    ImmutableLongArray(storage.plus(element))

public operator fun ImmutableLongArray.plus(elements: LongArray): ImmutableLongArray =
    ImmutableLongArray(storage.plus(elements))

public operator fun ImmutableLongArray.plus(elements: ImmutableLongArray): ImmutableLongArray =
    ImmutableLongArray(storage.plus(elements.storage))

public operator fun ImmutableLongArray.plus(elements: Collection<Long>): ImmutableLongArray =
    ImmutableLongArray(storage.plus(elements))

public fun ImmutableLongArray.reversedArray(): ImmutableLongArray =
    ImmutableLongArray(storage.reversedArray())

public fun ImmutableLongArray.sliceArray(indices: Collection<Int>): ImmutableLongArray =
    ImmutableLongArray(storage.sliceArray(indices))

public fun ImmutableLongArray.sliceArray(indices: IntRange): ImmutableLongArray =
    ImmutableLongArray(storage.sliceArray(indices))

public fun ImmutableLongArray.sortedArray(): ImmutableLongArray =
    ImmutableLongArray(storage.sortedArray())

public fun ImmutableLongArray.sortedArrayDescending(): ImmutableLongArray =
    ImmutableLongArray(storage.sortedArrayDescending())
