//
// NOTE: This file is auto-generated and may not be edited manually.
//
@file:Suppress("detekt:TooManyFunctions")

package de.jessestricker.kommons.array

import kotlin.Boolean
import kotlin.Char
import kotlin.CharArray
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.CharIterator
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlin.ranges.IntRange

/** An immutable array of chars. */
@JvmInline
public value class ImmutableCharArray internal constructor(internal val storage: CharArray) {
    /** The number of elements in the array. */
    public val size: Int
        get() = storage.size

    /** Creates a new array of the specified [size], with all elements initialized to zero. */
    public constructor(size: Int) : this(CharArray(size))

    /**
     * Creates a new array of the specified [size], where each element is calculated by calling the
     * specified [init] function.
     */
    public constructor(size: Int, `init`: (Int) -> Char) : this(CharArray(size, `init`))

    /** Returns the array element at the given [index]. */
    @Suppress("detekt:FunctionNaming") public operator fun `get`(index: Int): Char = storage[index]

    /** Creates a specialized [CharIterator] for iterating over the elements of the array. */
    public operator fun iterator(): CharIterator = storage.iterator()
}

public val ImmutableCharArray.indices: IntRange
    get() = storage.indices

public val ImmutableCharArray.lastIndex: Int
    get() = storage.lastIndex

@DelicateKommonsArrayApi
public fun CharArray.asImmutableArray(): ImmutableCharArray = ImmutableCharArray(this)

@DelicateKommonsArrayApi public fun ImmutableCharArray.asMutableArray(): CharArray = storage

public fun CharArray.toImmutableArray(): ImmutableCharArray = ImmutableCharArray(copyOf())

public fun ImmutableCharArray.toMutableArray(): CharArray = storage.copyOf()

public fun ImmutableCharArray.asList(): List<Char> = storage.asList()

public operator fun ImmutableCharArray.contains(element: Char): Boolean = storage.contains(element)

public fun ImmutableCharArray.indexOf(element: Char): Int = storage.indexOf(element)

public fun ImmutableCharArray.lastIndexOf(element: Char): Int = storage.lastIndexOf(element)

public infix fun ImmutableCharArray?.contentEquals(other: ImmutableCharArray?): Boolean =
    this?.storage.contentEquals(other?.storage)

public fun ImmutableCharArray?.contentHashCode(): Int = this?.storage.contentHashCode()

public fun ImmutableCharArray?.contentToString(): String = this?.storage.contentToString()

public fun ImmutableCharArray.copyInto(
    destination: CharArray,
    destinationOffset: Int = 0,
    startIndex: Int = 0,
    endIndex: Int = size,
): CharArray = storage.copyInto(destination, destinationOffset, startIndex, endIndex)

public fun ImmutableCharArray.copyOf(): ImmutableCharArray = ImmutableCharArray(storage.copyOf())

public fun ImmutableCharArray.copyOf(newSize: Int): ImmutableCharArray =
    ImmutableCharArray(storage.copyOf(newSize))

public fun ImmutableCharArray.copyOfRange(fromIndex: Int, toIndex: Int): ImmutableCharArray =
    ImmutableCharArray(storage.copyOfRange(fromIndex, toIndex))

public fun ImmutableCharArray.isEmpty(): Boolean = storage.isEmpty()

public fun ImmutableCharArray.isNotEmpty(): Boolean = storage.isNotEmpty()

public operator fun ImmutableCharArray.plus(element: Char): ImmutableCharArray =
    ImmutableCharArray(storage.plus(element))

public operator fun ImmutableCharArray.plus(elements: CharArray): ImmutableCharArray =
    ImmutableCharArray(storage.plus(elements))

public operator fun ImmutableCharArray.plus(elements: ImmutableCharArray): ImmutableCharArray =
    ImmutableCharArray(storage.plus(elements.storage))

public operator fun ImmutableCharArray.plus(elements: Collection<Char>): ImmutableCharArray =
    ImmutableCharArray(storage.plus(elements))

public fun ImmutableCharArray.reversedArray(): ImmutableCharArray =
    ImmutableCharArray(storage.reversedArray())

public fun ImmutableCharArray.sliceArray(indices: Collection<Int>): ImmutableCharArray =
    ImmutableCharArray(storage.sliceArray(indices))

public fun ImmutableCharArray.sliceArray(indices: IntRange): ImmutableCharArray =
    ImmutableCharArray(storage.sliceArray(indices))

public fun ImmutableCharArray.sortedArray(): ImmutableCharArray =
    ImmutableCharArray(storage.sortedArray())

public fun ImmutableCharArray.sortedArrayDescending(): ImmutableCharArray =
    ImmutableCharArray(storage.sortedArrayDescending())
