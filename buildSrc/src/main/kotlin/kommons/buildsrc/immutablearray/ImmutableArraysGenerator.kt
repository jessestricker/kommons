@file:Suppress("LongMethod", "TooManyFunctions")

package kommons.buildsrc.immutablearray

import java.io.File
import kommons.buildsrc.codegen.PrimitiveType
import kommons.buildsrc.codegen.buildKotlinFileContent
import kommons.buildsrc.codegen.writeTo

object ImmutableArraysGenerator {
    fun generate(outputDirectory: File) {
        for (type in PrimitiveType.values()) {
            val immutableArray = ImmutableArray(type)

            val fileName = immutableArray.immutableArrayType + ".kt"
            val fileContent = buildKotlinFileContent {
                generatedBy = ImmutableArraysGenerator::class
                annotateFile("""Suppress("TooManyFunctions")""")
                packageName = "kommons"
                import("kommons.internal.requireIndex")

                declare(immutableArray.classDefinition())
                declare(immutableArray.constructors())
                declare(immutableArray.immutableArrayOf())
                declare(immutableArray.toImmutableArray())

                declare(immutableArray.isEmpty())
                declare(immutableArray.isNotEmpty())
                declare(immutableArray.lastIndex())
                declare(immutableArray.indices())

                declare(immutableArray.asList())
                declare(immutableArray.contains())
                declare(immutableArray.indexOf())
                declare(immutableArray.lastIndexOf())
                declare(immutableArray.sliceArray())
                declare(immutableArray.toMutableArray())
            }
            fileContent.writeTo(outputDirectory, fileName)
        }
    }
}

private class ImmutableArray(val elementType: PrimitiveType) {
    val arrayType: String
        get() = "${elementType}Array"

    val immutableArrayType: String
        get() = "Immutable${elementType}Array"

    val optInAnnotation: String
        get() = if (elementType.isUnsigned) "@ExperimentalUnsignedTypes" else ""
}

// language=kotlin
private fun ImmutableArray.classDefinition(): String {
    val iteratorType =
        if (elementType.isUnsigned) "Iterator<$elementType>" else "${elementType}Iterator"
    val extendIterator = if (elementType.isUnsigned) iteratorType else "${iteratorType}()"
    val iteratorNext = if (elementType.isUnsigned) "next" else "next$elementType"
    val arraySame = if (elementType.isUnsigned) "==" else "==="

    return """
    /** An immutable array of ${elementType.docNamePlural}. */
    $optInAnnotation
    public class $immutableArrayType
    @PublishedApi
    internal constructor(
        internal val data: $arrayType,
        internal val dataStart: Int = 0,
        internal val dataEnd: Int = data.size,
    ) {
        // 0 <= dataStart <= dataEnd <= data.size
    
        /** The number of elements. */
        public val size: Int
            get() = dataEnd - dataStart
    
        /**
         * Returns the element at the given [index].
         *
         * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
         */
        public operator fun get(index: Int): $elementType {
            requireIndex(index, size)
            return data[dataStart + index]
        }
    
        /** Returns an iterator over the elements. */
        public operator fun iterator(): $iteratorType {
            return object : $extendIterator {
                private var dataIndex = dataStart
    
                override fun hasNext(): Boolean {
                    return dataIndex < dataEnd
                }
    
                override fun $iteratorNext(): $elementType {
                    if (!hasNext()) {
                        throw NoSuchElementException()
                    }
                    return data[dataIndex++]
                }
            }
        }
    
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false
            other as $immutableArrayType
    
            if (data $arraySame other.data && dataStart == other.dataStart && dataEnd == other.dataEnd) return true
            if (size != other.size) return false
    
            var dataIndex = dataStart
            var otherDataIndex = other.dataStart
            while (dataIndex < dataEnd) {
                if (data[dataIndex++] != other.data[otherDataIndex++]) {
                    return false
                }
            }
            return true
        }
    
        override fun hashCode(): Int {
            var result = 1
            for (dataIndex in dataStart..<dataEnd) {
                result = 31 * result + data[dataIndex].hashCode()
            }
            return result
        }
    
        override fun toString(): String {
            return "$immutableArrayType(size=${'$'}size)"
        }
    }
    """
        .trimIndent()
}

// language=kotlin
private fun ImmutableArray.constructors() =
    """
    /**
     * Creates an immutable array of ${elementType.docNamePlural} of the given [size],
     * with every element initialized to `${elementType.defaultValue}`.
     */
    $optInAnnotation
    public fun $immutableArrayType(size: Int): $immutableArrayType {
        return $immutableArrayType($arrayType(size))
    }
    
    /**
     * Creates an immutable array of ${elementType.docNamePlural} of the given [size],
     * with every element initialized by the given [init] function.
     */
    $optInAnnotation
    public inline fun $immutableArrayType(size: Int, init: (index: Int) -> $elementType): $immutableArrayType {
        return $immutableArrayType($arrayType(size) { init(it) })
    }
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.immutableArrayOf() =
    """
    /** Creates a new immutable array of ${elementType.docNamePlural} which contains the given [elements]. */
    $optInAnnotation
    public fun immutable${arrayType}Of(vararg elements: $elementType): $immutableArrayType {
        return $immutableArrayType(elements)
    }
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.toImmutableArray() =
    """
    /** Returns a new immutable array which contains the elements of this array. */
    $optInAnnotation
    public fun $arrayType.toImmutableArray(): $immutableArrayType {
        return $immutableArrayType(this.copyOf())
    }
    
    /**
     * Returns a new immutable array which contains the elements of this array from given [startIndex]
     * (inclusive) to the given [endIndex] (exclusive).
     *
     * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
     * than [endIndex], or [endIndex] is greater than [size][$immutableArrayType.size].
     */
    $optInAnnotation
    public fun $arrayType.toImmutableArray(startIndex: Int, endIndex: Int): $immutableArrayType {
        return $immutableArrayType(this.copyOfRange(startIndex, endIndex))
    }
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.isEmpty() =
    """
    /** Returns whether this array is empty. */
    $optInAnnotation
    public fun $immutableArrayType.isEmpty(): Boolean {
        return size == 0
    }
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.isNotEmpty() =
    """
    /** Returns whether this array is not empty. */
    $optInAnnotation
    public fun $immutableArrayType.isNotEmpty(): Boolean {
        return !isEmpty()
    }
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.lastIndex() =
    """
    /** The last valid index. */
    $optInAnnotation
    public val $immutableArrayType.lastIndex: Int
        get() = size - 1
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.indices() =
    """
    /** The range of valid indices. */
    $optInAnnotation
    public val $immutableArrayType.indices: IntRange
        get() = IntRange(0, lastIndex)
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.asList() =
    """
    /** Returns an immutable [List] which contains the elements of this array. */
    $optInAnnotation
    public fun $immutableArrayType.asList(): List<$elementType> {
        return object : AbstractList<$elementType>(), RandomAccess {
            override val size: Int get() = this@asList.size
            override fun contains(element: $elementType): Boolean = this@asList.contains(element)
            override fun get(index: Int): $elementType = this@asList[index]
            override fun indexOf(element: $elementType): Int = this@asList.indexOf(element)
            override fun isEmpty(): Boolean = this@asList.isEmpty()
            override fun iterator(): Iterator<$elementType> = this@asList.iterator()
            override fun lastIndexOf(element: $elementType): Int = this@asList.lastIndexOf(element)
        }
    }
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.contains() =
    """
    /** Returns whether this array contains the given [element]. */
    $optInAnnotation
    public operator fun $immutableArrayType.contains(element: $elementType): Boolean {
        return indexOf(element) != -1
    }
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.indexOf() =
    """
    /**
     * Returns the index of the first occurrence of the given [value] in this array,
     * or -1 if this array does not contain the given value.
     */
    $optInAnnotation
    public fun $immutableArrayType.indexOf(value: $elementType): Int {
        for (dataIndex in dataStart..<dataEnd) {
            if (value == data[dataIndex]) {
                return dataIndex - dataStart
            }
        }
        return -1
    }
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.lastIndexOf() =
    """
    /**
     * Returns the index of the last occurrence of the given [value] in this array,
     * or -1 if this array does not contain the given value.
     */
    $optInAnnotation
    public fun $immutableArrayType.lastIndexOf(value: $elementType): Int {
        for (dataIndex in (dataStart..<dataEnd).reversed()) {
            if (value == data[dataIndex]) {
                return dataIndex - dataStart
            }
        }
        return -1
    }
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.sliceArray() =
    """
    /**
     * Returns a new immutable array which contains the elements of this array from the given
     * [startIndex] (inclusive) to the given [endIndex] (exclusive).
     *
     * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
     * than [endIndex], or [endIndex] is greater than [size][$immutableArrayType.size].
     */
    $optInAnnotation
    public fun $immutableArrayType.sliceArray(startIndex: Int, endIndex: Int): $immutableArrayType {
        // 0 <= startIndex <= endIndex <= size
        require(0 <= startIndex) { "startIndex ${'$'}startIndex must be greater than or equal to 0" }
        require(startIndex <= endIndex) {
            "startIndex ${'$'}startIndex must be less than or equal to endIndex ${'$'}endIndex"
        }
        require(endIndex <= size) { "endIndex ${'$'}endIndex must be less than or equal to size ${'$'}size" }
    
        return $immutableArrayType(data, dataStart + startIndex, dataStart + endIndex)
    }
    """
        .trimIndent()

// language=kotlin
private fun ImmutableArray.toMutableArray() =
    """
    /** Returns a new mutable array which contains the elements of this array. */
    $optInAnnotation
    public fun $immutableArrayType.toMutableArray(): $arrayType {
        return data.copyOfRange(dataStart, dataEnd)
    }
    """
        .trimIndent()
