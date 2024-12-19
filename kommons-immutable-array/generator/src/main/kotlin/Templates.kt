@file:Suppress("detekt:TooManyFunctions")

class Templates(val type: PrimitiveType) {
    val arrayType: String
        get() = "${type}Array"

    val immutableArrayType: String
        get() = "Immutable${arrayType}"

    val experimentalAnnotation: String
        get() = if (type.isUnsigned) "@ExperimentalUnsignedTypes" else ""
}

fun Templates.arrayContentEquals() =
    kotlinCode(
        """
        /**
         * Checks if the contents of this array
         * from the given [startIndex] (inclusive) to the given [endIndex] (exclusive)
         * are equal to the contents of the given [other] array
         * from the given [otherStartIndex] (inclusive) to the given [otherEndIndex] (exclusive).
         */
        $experimentalAnnotation
        public fun $arrayType.contentEquals(
            startIndex: Int,
            endIndex: Int,
            other: $arrayType,
            otherStartIndex: Int,
            otherEndIndex: Int,
        ): Boolean {
            // check for same arrays with equal ranges
            if (this == other && startIndex == otherStartIndex && endIndex == otherEndIndex) {
                return true
            }
        
            // check for size mismatch
            val size = endIndex - startIndex
            val otherSize = otherEndIndex - otherStartIndex
            if (size != otherSize) {
                return false
            }
        
            // check elements
            var index = startIndex
            var otherIndex = otherStartIndex
            while (index < endIndex) {
                if (this[index++] != this[otherIndex++]) {
                    return false
                }
            }
            return true
        }
        """
            .trimIndent()
    )

fun Templates.arrayContentHashCode() =
    kotlinCode(
        """
        /**
         * Returns a hash code based on the contents of this array
         * from the given [startIndex] (inclusive) to the given [endIndex] (exclusive).
         */
        $experimentalAnnotation
        public fun $arrayType.contentHashCode(
            startIndex: Int,
            endIndex: Int,
        ): Int {
            var result = 1
            for (index in startIndex..<endIndex) {
                result = 31 * result + this[index].hashCode()
            }
            return result
        }
        """
            .trimIndent()
    )

@Suppress("LongMethod")
fun Templates.immutableArrayClass(): KotlinCode {
    val iteratorType = if (type.isBuiltin) "${type}Iterator" else "Iterator<$type>"
    val extendIterator = if (type.isBuiltin) "${iteratorType}()" else iteratorType
    val iteratorNext = if (type.isBuiltin) "next$type" else "next"

    return kotlinCode(
        """
        /** An immutable array of ${type.docName}s. */
        $experimentalAnnotation
        public class $immutableArrayType
        @PublishedApi
        internal constructor(
            internal val data: $arrayType,
            internal val dataStart: Int = 0,
            internal val dataEnd: Int = data.size,
        ) {
            // 0 <= dataStart <= dataEnd <= data.size
        
            /**
             * Creates an immutable array of ${type.docName}s of the given [size],
             * with every element initialized to ${type.defaultValue}.
             */
            public constructor(size: Int) : this($arrayType(size))
        
            /** The number of elements. */
            public val size: Int
                get() = dataEnd - dataStart
        
            /**
             * Returns the element at the given [index].
             *
             * @throws[IndexOutOfBoundsException] if the given [index] is out of bounds.
             */
            public operator fun get(index: Int): $type {
                requireIndex(index in 0..<size) {
                    "index ${'$'}index must be within range 0..<${'$'}size"
                }
                return data[dataStart + index]
            }
        
            /** Returns an iterator over the elements. */
            public operator fun iterator(): $iteratorType {
                return object : $extendIterator {
                    private var dataIndex = dataStart
        
                    override fun hasNext(): Boolean {
                        return dataIndex < dataEnd
                    }
        
                    override fun $iteratorNext(): $type {
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
                return data.contentEquals(
                    dataStart,
                    dataEnd,
                    other.data,
                    other.dataStart,
                    other.dataEnd,
                )
            }
        
            override fun hashCode(): Int {
                return data.contentHashCode(dataStart, dataEnd)
            }
        
            override fun toString(): String {
                return "$immutableArrayType(size=${'$'}size)"
            }
        }
        
        /**
         * Creates an immutable array of ${type.docName}s of the given [size],
         * with every element initialized by the given [init] function.
         */
        $experimentalAnnotation
        public inline fun $immutableArrayType(size: Int, init: (index: Int) -> $type): $immutableArrayType {
            return $immutableArrayType($arrayType(size) { init(it) })
        }
        
        /** Creates an immutable array of ${type.docName}s which contains the given [elements]. */
        $experimentalAnnotation
        public fun immutable${type}ArrayOf(vararg elements: $type): $immutableArrayType {
            return $immutableArrayType(elements)
        }
        """
            .trimIndent()
    ) {
        import("kommons.internal.requireIndex")
    }
}

fun Templates.arrayToImmutableArray() =
    kotlinCode(
        """
        /** Returns a new immutable array which contains the elements of this array. */
        $experimentalAnnotation
        public fun $arrayType.toImmutableArray(): $immutableArrayType {
            return $immutableArrayType(this.copyOf())
        }
        
        /**
         * Returns a new immutable array which contains the elements of this array
         * from the given [startIndex] (inclusive) to the given [endIndex] (exclusive).
         *
         * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
         * than [endIndex], or [endIndex] is greater than [size][$immutableArrayType.size].
         */
        $experimentalAnnotation
        public fun $arrayType.toImmutableArray(startIndex: Int, endIndex: Int): $immutableArrayType {
            return $immutableArrayType(this.copyOfRange(startIndex, endIndex))
        }
        """
            .trimIndent()
    )

fun Templates.immutableArrayToArray() =
    kotlinCode(
        """
        /** Returns a new mutable array which contains the elements of this array. */
        $experimentalAnnotation
        public fun $immutableArrayType.toMutableArray(): $arrayType {
            return data.copyOfRange(dataStart, dataEnd)
        }
        """
            .trimIndent()
    )

fun Templates.immutableArraySliceArray() =
    kotlinCode(
        """
        /**
         * Returns a new immutable array which contains the elements of this array
         * from the given [startIndex] (inclusive) to the given [endIndex] (exclusive).
         *
         * @throws[IllegalArgumentException] if [startIndex] is less than zero, or [startIndex] is greater
         * than [endIndex], or [endIndex] is greater than [size][$immutableArrayType.size].
         */
        $experimentalAnnotation
        public fun $immutableArrayType.sliceArray(startIndex: Int, endIndex: Int): $immutableArrayType {
            require(0 <= startIndex) {
                "startIndex ${'$'}startIndex must be greater than or equal to 0"
            }
            require(startIndex <= endIndex) {
                "startIndex ${'$'}startIndex must be less than or equal to endIndex ${'$'}endIndex"
            }
            require(endIndex <= size) {
                "endIndex ${'$'}endIndex must be less than or equal to size ${'$'}size"
            }
            return $immutableArrayType(data, dataStart + startIndex, dataStart + endIndex)
        }
        """
            .trimIndent()
    )

fun Templates.immutableArrayLastIndex() =
    kotlinCode(
        """
        /** The last valid index. */
        $experimentalAnnotation
        public val $immutableArrayType.lastIndex: Int
            get() = size - 1
        """
            .trimIndent()
    )

fun Templates.immutableArrayIndices() =
    kotlinCode(
        """
        /** The range of valid indices. */
        $experimentalAnnotation
        public val $immutableArrayType.indices: IntRange
            get() = 0..<size
        """
            .trimIndent()
    )

fun Templates.immutableArrayIsEmpty() =
    kotlinCode(
        """
        /** Returns whether this array is empty. */
        $experimentalAnnotation
        public fun $immutableArrayType.isEmpty(): Boolean {
            return size == 0
        }
        """
            .trimIndent()
    )

fun Templates.immutableArrayIsNotEmpty() =
    kotlinCode(
        """
        /** Returns whether this array is not empty. */
        $experimentalAnnotation
        public fun $immutableArrayType.isNotEmpty(): Boolean {
            return size != 0
        }
        """
            .trimIndent()
    )

// language=kotlin
fun Templates.immutableArrayIndexOf() =
    kotlinCode(
        """
        /**
         * Returns the index of the first occurrence of the given [value] in this array,
         * or -1 if this array does not contain the given value.
         */
        $experimentalAnnotation
        public fun $immutableArrayType.indexOf(value: $type): Int {
            for (dataIndex in dataStart..<dataEnd) {
                if (value == data[dataIndex]) {
                    return dataIndex - dataStart
                }
            }
            return -1
        }
        """
            .trimIndent()
    )

// language=kotlin
fun Templates.immutableArrayLastIndexOf() =
    kotlinCode(
        """
        /**
         * Returns the index of the last occurrence of the given [value] in this array,
         * or -1 if this array does not contain the given value.
         */
        $experimentalAnnotation
        public fun $immutableArrayType.lastIndexOf(value: $type): Int {
            for (dataIndex in (dataStart..<dataEnd).reversed()) {
                if (value == data[dataIndex]) {
                    return dataIndex - dataStart
                }
            }
            return -1
        }
        """
            .trimIndent()
    )

// language=kotlin
fun Templates.immutableArrayContains() =
    kotlinCode(
        """
        /** Returns whether this array contains the given [element]. */
        $experimentalAnnotation
        public operator fun $immutableArrayType.contains(element: $type): Boolean {
            return indexOf(element) != -1
        }
        """
            .trimIndent()
    )

// language=kotlin
fun Templates.immutableArrayAsList() =
    kotlinCode(
        """
        /** Returns an immutable [List] which contains the elements of this array. */
        $experimentalAnnotation
        public fun $immutableArrayType.asList(): List<$type> {
            return object : AbstractList<$type>(), RandomAccess {
                override val size: Int get() = this@asList.size
                override fun contains(element: $type): Boolean = this@asList.contains(element)
                override fun get(index: Int): $type = this@asList[index]
                override fun indexOf(element: $type): Int = this@asList.indexOf(element)
                override fun isEmpty(): Boolean = this@asList.isEmpty()
                override fun iterator(): Iterator<$type> = this@asList.iterator()
                override fun lastIndexOf(element: $type): Int = this@asList.lastIndexOf(element)
            }
        }
        """
            .trimIndent()
    )
