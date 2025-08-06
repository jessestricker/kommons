//
// NOTE: This file is auto-generated and may not be edited manually.
//
@file:Suppress("detekt:TooManyFunctions")

package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.Int
import kotlin.IntArray
import kotlin.String
import kotlin.Suppress
import kotlin.collections.Collection
import kotlin.collections.IntIterator
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of ints. */
@JvmInline
public value class ImmutableIntArray internal constructor(internal val storage: IntArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(IntArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> Int) : this(IntArray(size, `init`))

    /** Returns the array element at the given [index]. */
    @Suppress("detekt:FunctionNaming") public operator fun `get`(index: Int): Int = storage[index]

    /** Creates a specialized [IntIterator] for iterating over the elements of the array. */
    public operator fun iterator(): IntIterator = storage.iterator()
}

public val ImmutableIntArray.indices: IntRange
    get() = storage.indices

public val ImmutableIntArray.lastIndex: Int
    get() = storage.lastIndex

@DelicateKommonsArrayApi
public fun IntArray.asImmutableArray(): ImmutableIntArray = ImmutableIntArray(this)

@DelicateKommonsArrayApi public fun ImmutableIntArray.asMutableArray(): IntArray = storage

public fun IntArray.toImmutableArray(): ImmutableIntArray = ImmutableIntArray(copyOf())

public fun ImmutableIntArray.toMutableArray(): IntArray = storage.copyOf()

public fun ImmutableIntArray.asList(): List<Int> = storage.asList()

public operator fun ImmutableIntArray.contains(element: Int): Boolean = storage.contains(element)

public fun ImmutableIntArray.indexOf(element: Int): Int = storage.indexOf(element)

public fun ImmutableIntArray.lastIndexOf(element: Int): Int = storage.lastIndexOf(element)

public infix fun ImmutableIntArray?.contentEquals(other: ImmutableIntArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

public fun ImmutableIntArray?.contentHashCode(): Int = this?.storage.contentHashCode()

public fun ImmutableIntArray?.contentToString(): String = this?.storage.contentToString()

public fun ImmutableIntArray.copyInto(
    destination: IntArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): IntArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

public fun ImmutableIntArray.copyOf(): ImmutableIntArray = ImmutableIntArray(storage.copyOf())

public fun ImmutableIntArray.copyOf(newSize: Int): ImmutableIntArray =
    ImmutableIntArray(storage.copyOf(newSize))

public fun ImmutableIntArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableIntArray =
    ImmutableIntArray(storage.copyOfRange(fromIndex, toIndex))

public fun ImmutableIntArray.isEmpty(): Boolean = storage.isEmpty()

public fun ImmutableIntArray.isNotEmpty(): Boolean = storage.isNotEmpty()

public operator fun ImmutableIntArray.plus(element: Int): ImmutableIntArray =
    ImmutableIntArray(storage.plus(element))

public operator fun ImmutableIntArray.plus(elements: IntArray): ImmutableIntArray =
    ImmutableIntArray(storage.plus(elements))

public operator fun ImmutableIntArray.plus(elements: ImmutableIntArray): ImmutableIntArray =
    ImmutableIntArray(storage.plus(elements.storage))

public operator fun ImmutableIntArray.plus(elements: Collection<Int>): ImmutableIntArray =
    ImmutableIntArray(storage.plus(elements))

public fun ImmutableIntArray.reversedArray(): ImmutableIntArray =
    ImmutableIntArray(storage.reversedArray())

public fun ImmutableIntArray.sliceArray(indices: Collection<Int>): ImmutableIntArray =
    ImmutableIntArray(storage.sliceArray(indices))

public fun ImmutableIntArray.sliceArray(indices: IntRange): ImmutableIntArray =
    ImmutableIntArray(storage.sliceArray(indices))

public fun ImmutableIntArray.sortedArray(): ImmutableIntArray =
    ImmutableIntArray(storage.sortedArray())

public fun ImmutableIntArray.sortedArrayDescending(): ImmutableIntArray =
    ImmutableIntArray(storage.sortedArrayDescending())
