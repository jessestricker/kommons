import PrimitiveType.Companion.isFloatingPoint
import PrimitiveType.Companion.isUnsigned
import org.intellij.lang.annotations.Language

class ImmutableArrayCode(private val elementType: PrimitiveType) {
    val arrayType: String
        get() = "${elementType}Array"

    val iteratorType: String
        get() =
            if (!elementType.isUnsigned) "${elementType}Iterator" else "Iterator<${elementType}>"

    val immutableArrayType: String
        get() = "Immutable${elementType}Array"

    val experimentalUnsignedAnnotation: String
        get() = if (elementType.isUnsigned) "@ExperimentalUnsignedTypes" else ""

    @Language("kotlin")
    fun definition(): String =
        """
        /**
         * An immutable array of ${elementType.humanName}s.
         *
         * When targeting the JVM, instances of this class are represented as `${elementType.javaType}[]`.
         *
         * @see $arrayType
         */
        $experimentalUnsignedAnnotation
        @JvmInline
        public value class $immutableArrayType
        internal constructor(
            internal val data: $arrayType,
        ) {
            /**
             * Creates a new immutable array of the specified [size], with all elements initialized to zero.
             *
             * @throws RuntimeException if the specified [size] is negative.
             */
            public constructor(size: Int) : this($arrayType(size))
        
            /**
             * Creates a new immutable array of the specified [size], where each element is calculated by
             * calling the specified [init] function.
             *
             * @throws RuntimeException if the specified [size] is negative.
             */
            public constructor(size: Int, init: (index: Int) -> $elementType) : this($arrayType(size, init))
        
            /** The number of elements. */
            public val size: Int
                get() = data.size
        
            /**
             * Returns the element at the given [index].
             *
             * @throws IndexOutOfBoundsException if the [index] is out of bounds.
             */
            public operator fun get(index: Int): $elementType {
                return data[index]
            }
        
            /**
             * Creates a specialized iterator for iterating over the elements.
             *
             * @see $iteratorType
             */
            public operator fun iterator(): $iteratorType {
                return data.iterator()
            }
        
            public companion object {
                /**
                 * Returns a new immutable wrapping the given [mutable array][mutableArray].
                 *
                 * **Note:** This function is marked as _delicate_ because it could potentially break the
                 * contract of immutability. Only use this function if you can ensure that the given
                 * [mutable array][mutableArray] is not modified during the lifetime of the returned
                 * immutable array.
                 */
                @DelicateArraysApi
                public fun wrap(mutableArray: $arrayType): $immutableArrayType {
                    return $immutableArrayType(mutableArray)
                }
            }
        }
        """
            .trimIndent()

    @Language("kotlin")
    fun constructors(): String =
        """
        /** Returns a new immutable array which contains the given [elements]. */
        $experimentalUnsignedAnnotation
        public fun immutable${elementType}ArrayOf(vararg elements: $elementType): $immutableArrayType {
            return $immutableArrayType(elements)
        }
        
        /** Returns a new immutable array which is a copy of this array. */
        $experimentalUnsignedAnnotation
        public fun $arrayType.toImmutableArray(): $immutableArrayType {
            return $immutableArrayType(copyOf())
        }
        
        /**
         * Returns a new immutable array which is a copy of the specified range of this array.
         *
         * @param fromIndex the start of the range (inclusive) to copy.
         * @param toIndex the end of the range (exclusive) to copy.
         * @throws IndexOutOfBoundsException if [fromIndex] is less than zero 
         *   or [toIndex] is greater than the size of this array.
         * @throws IllegalArgumentException if [fromIndex] is greater than [toIndex].
         */
        $experimentalUnsignedAnnotation
        public fun $arrayType.toImmutableArray(fromIndex: Int, toIndex: Int): $immutableArrayType {
            return $immutableArrayType(copyOfRange(fromIndex, toIndex))
        }
        """
            .trimIndent()

    @Language("kotlin")
    fun conversions(): String =
        """
        $experimentalUnsignedAnnotation
        /** Returns an immutable list that wraps this array. */
        public fun $immutableArrayType.asList(): List<$elementType> {
            return data.asList()
        }
        
        /**
         * Returns a new immutable array which is a copy of the specified range of this array.
         *
         * @param fromIndex the start of the range (inclusive) to copy.
         * @param toIndex the end of the range (exclusive) to copy.
         * @throws IndexOutOfBoundsException if [fromIndex] is less than zero 
         *   or [toIndex] is greater than the size of this array.
         * @throws IllegalArgumentException if [fromIndex] is greater than [toIndex].
         */
        $experimentalUnsignedAnnotation
        public fun $immutableArrayType.copyOfRange(fromIndex: Int, toIndex: Int): $immutableArrayType {
            return $immutableArrayType(data.copyOfRange(fromIndex, toIndex))
        }
        
        /** Returns a new mutable array which is a copy of this array. */
        $experimentalUnsignedAnnotation
        public fun $immutableArrayType.toMutableArray(): $arrayType {
            return data.copyOf()
        }
        
        /**
         * Returns a new mutable array which is a copy of the specified range of this array.
         *
         * @param fromIndex the start of the range (inclusive) to copy.
         * @param toIndex the end of the range (exclusive) to copy.
         * @throws IndexOutOfBoundsException if [fromIndex] is less than zero 
         *   or [toIndex] is greater than the size of this array.
         * @throws IllegalArgumentException if [fromIndex] is greater than [toIndex].
         */
        $experimentalUnsignedAnnotation
        public fun $immutableArrayType.toMutableArray(fromIndex: Int, toIndex: Int): $arrayType {
            return data.copyOfRange(fromIndex, toIndex)
        }
        """
            .trimIndent()

    @Language("kotlin")
    fun properties(): String =
        """
        /** Returns the range of valid indices. */
        $experimentalUnsignedAnnotation
        public val $immutableArrayType.indices: IntRange
            get() = data.indices
        
        /** Returns the last valid index. */
        $experimentalUnsignedAnnotation
        public val $immutableArrayType.lastIndex: Int
            get() = data.lastIndex
        
        /** Returns `true` if this array is empty. */
        $experimentalUnsignedAnnotation
        public fun $immutableArrayType.isEmpty(): Boolean {
            return data.isEmpty()
        }
        
        /** Returns `true` if this array is not empty. */
        $experimentalUnsignedAnnotation
        public fun $immutableArrayType.isNotEmpty(): Boolean {
            return data.isNotEmpty()
        }
        """
            .trimIndent()

    @Language("kotlin")
    fun contentFunctions(): String =
        """
        /** Checks if the two arrays are equal to each other based on their contents. */
        $experimentalUnsignedAnnotation
        public infix fun $immutableArrayType?.contentEquals(other: $immutableArrayType?): Boolean {
            return this?.data contentEquals other?.data
        }
        
        /** Returns a hash code based on the contents of this array. */
        $experimentalUnsignedAnnotation
        public fun $immutableArrayType?.contentHashCode(): Int {
            return this?.data.contentHashCode()
        }
        
        /** Returns a string representation of the contents of this array. */
        $experimentalUnsignedAnnotation
        public fun $immutableArrayType?.contentToString(): String {
            return this?.data.contentToString()
        }
        """
            .trimIndent()

    @Language("kotlin")
    fun elementSearchFunctions(): String? =
        if (elementType.isFloatingPoint) null
        else
            """
        /**
         * Returns `true` if the given [element] is found in this array.
         */
        $experimentalUnsignedAnnotation
        public operator fun $immutableArrayType.contains(element: $elementType): Boolean {
            return data.contains(element)
        }
        
        /**
         * Returns the index of the first occurrence of the given [element], 
         * or -1 if this array does not contain the element.
         */
        $experimentalUnsignedAnnotation
        public fun $immutableArrayType.indexOf(element: $elementType): Int {
            return data.indexOf(element)
        }
        
        /**
         * Returns the index of the last occurrence of the given [element], 
         * or -1 if this array does not contain the element.
         */
        $experimentalUnsignedAnnotation
        public fun $immutableArrayType.lastIndexOf(element: $elementType): Int {
            return data.lastIndexOf(element)
        }
        """
                .trimIndent()

    override fun toString(): String {
        val elements =
            listOfNotNull(
                definition(),
                constructors(),
                conversions(),
                properties(),
                contentFunctions(),
                elementSearchFunctions(),
            )
        return elements.joinToString(separator = "\n") { it.ensureEndsWithNewline() }
    }
}
