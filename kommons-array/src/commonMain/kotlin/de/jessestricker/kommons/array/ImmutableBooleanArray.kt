//
// NOTE: This file is auto-generated and may not be edited manually.
//
package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.BooleanArray
import kotlin.Int
import kotlin.String
import kotlin.collections.BooleanIterator
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of booleans. */
@JvmInline
public value class ImmutableBooleanArray internal constructor(internal val storage: BooleanArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(BooleanArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> Boolean) : this(BooleanArray(size, `init`))

    /** Returns the array element at the given [index]. */
    public operator fun `get`(index: Int): Boolean = storage[index]

    /** Creates a specialized [BooleanIterator] for iterating over the elements of the array. */
    public operator fun iterator(): BooleanIterator = storage.iterator()
}

public val ImmutableBooleanArray.indices: IntRange
    get() = storage.indices

public val ImmutableBooleanArray.lastIndex: Int
    get() = storage.lastIndex

@DelicateKommonsArrayApi
public fun BooleanArray.asImmutableArray(): ImmutableBooleanArray = ImmutableBooleanArray(this)

@DelicateKommonsArrayApi public fun ImmutableBooleanArray.asMutableArray(): BooleanArray = storage

public fun BooleanArray.toImmutableArray(): ImmutableBooleanArray = ImmutableBooleanArray(copyOf())

public fun ImmutableBooleanArray.toMutableArray(): BooleanArray = storage.copyOf()

public fun ImmutableBooleanArray.asList(): List<Boolean> = storage.asList()

public operator fun ImmutableBooleanArray.contains(element: Boolean): Boolean =
    storage.contains(element)

public fun ImmutableBooleanArray.indexOf(element: Boolean): Int = storage.indexOf(element)

public fun ImmutableBooleanArray.lastIndexOf(element: Boolean): Int = storage.lastIndexOf(element)

public infix fun ImmutableBooleanArray?.contentEquals(other: ImmutableBooleanArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

public fun ImmutableBooleanArray?.contentHashCode(): Int = this?.storage.contentHashCode()

public fun ImmutableBooleanArray?.contentToString(): String = this?.storage.contentToString()

public fun ImmutableBooleanArray.copyInto(
    destination: BooleanArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): BooleanArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

public fun ImmutableBooleanArray.copyOf(): ImmutableBooleanArray =
    ImmutableBooleanArray(storage.copyOf())

public fun ImmutableBooleanArray.copyOf(newSize: Int): ImmutableBooleanArray =
    ImmutableBooleanArray(storage.copyOf(newSize))

public fun ImmutableBooleanArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableBooleanArray =
    ImmutableBooleanArray(storage.copyOfRange(fromIndex, toIndex))

public fun ImmutableBooleanArray.isEmpty(): Boolean = storage.isEmpty()

public fun ImmutableBooleanArray.isNotEmpty(): Boolean = storage.isNotEmpty()

public operator fun ImmutableBooleanArray.plus(element: Boolean): ImmutableBooleanArray =
    ImmutableBooleanArray(storage.plus(element))

public operator fun ImmutableBooleanArray.plus(elements: BooleanArray): ImmutableBooleanArray =
    ImmutableBooleanArray(storage.plus(elements))

public operator fun ImmutableBooleanArray.plus(
    elements: ImmutableBooleanArray
): ImmutableBooleanArray = ImmutableBooleanArray(storage.plus(elements.storage))

public operator fun ImmutableBooleanArray.plus(
    elements: Collection<Boolean>
): ImmutableBooleanArray = ImmutableBooleanArray(storage.plus(elements))

public fun ImmutableBooleanArray.reversedArray(): ImmutableBooleanArray =
    ImmutableBooleanArray(storage.reversedArray())

public fun ImmutableBooleanArray.sliceArray(indices: Collection<Int>): ImmutableBooleanArray =
    ImmutableBooleanArray(storage.sliceArray(indices))

public fun ImmutableBooleanArray.sliceArray(indices: IntRange): ImmutableBooleanArray =
    ImmutableBooleanArray(storage.sliceArray(indices))
