import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlin.io.path.Path

fun main(args: Array<String>) {
    val outputDir = Path(args[0])
    for (generator in GENERATORS) {
        generator.file.writeTo(outputDir)
    }
}

val GENERATORS =
    listOf(
        Generator(BOOLEAN, "booleans", BOOLEAN_ARRAY, BooleanIterator::class.asTypeName()),
        Generator(BYTE, "bytes", BYTE_ARRAY, ByteIterator::class.asTypeName()),
        Generator(SHORT, "shorts", SHORT_ARRAY, ShortIterator::class.asTypeName()),
        Generator(INT, "ints", INT_ARRAY, IntIterator::class.asTypeName()),
        Generator(LONG, "longs", LONG_ARRAY, LongIterator::class.asTypeName()),
        Generator(CHAR, "chars", CHAR_ARRAY, CharIterator::class.asTypeName()),
        Generator(FLOAT, "floats", FLOAT_ARRAY, FloatIterator::class.asTypeName()),
        Generator(DOUBLE, "doubles", DOUBLE_ARRAY, DoubleIterator::class.asTypeName()),
    )

class Generator(
    val elementType: ClassName,
    val elementPluralName: String,
    val arrayType: ClassName,
    val iteratorType: ClassName,
) {
    companion object {
        const val PACKAGE_NAME = "de.jessestricker.kommons.array"
        val DELICATE_API_ANNOTATION = ClassName(PACKAGE_NAME, "DelicateKommonsArrayApi")
        val SORTABLE_TYPES = setOf(BYTE, SHORT, INT, LONG, CHAR, FLOAT, DOUBLE)
        val FLOATING_POINT_TYPES = setOf(FLOAT, DOUBLE)
    }

    val immutableArrayType = ClassName(PACKAGE_NAME, "Immutable${arrayType.simpleName}")

    val storage = MemberName(immutableArrayType, "storage")
    val size = MemberName(immutableArrayType, "size")

    val immutableArray =
        ClassSpec(immutableArrayType) {
            addKdoc("An immutable array of %L.", elementPluralName)
            addAnnotation(JvmInline::class)
            addModifiers(KModifier.VALUE)

            val storageParam = ParameterSpec("storage", arrayType)
            primaryConstructor {
                addModifiers(KModifier.INTERNAL)
                addParameter(storageParam)
            }
            property(storage.simpleName, arrayType) {
                initializer("%N", storageParam)
                addModifiers(KModifier.INTERNAL)
            }

            property(size.simpleName, INT) {
                addKdoc("The number of elements in the array.")
                getter { addStatement("return %N.size", storage) }
            }

            secondaryConstructor {
                val size = parameter("size", INT)
                addKdoc(
                    "Creates a new array of the specified [%N], with all elements initialized to zero.",
                    size,
                )
                callThisConstructor(CodeBlock.of("%T(%N)", arrayType, size))
            }
            secondaryConstructor {
                val size = parameter("size", INT)
                val init = parameter("init", lambdaTypeOf(listOf(INT), elementType))
                addKdoc(
                    "Creates a new array of the specified [%N], where each element is calculated by calling the specified [%L] function.",
                    size,
                    init.name,
                )
                callThisConstructor(CodeBlock.of("%T(%N, %N)", arrayType, size, init))
            }
            function("get") {
                addModifiers(KModifier.OPERATOR)
                val index = parameter("index", INT)
                addKdoc("Returns the array element at the given [%N].", index)
                returns(elementType)
                addStatement("return %N[%N]", storage, index)
            }
            function("iterator") {
                addKdoc(
                    "Creates a specialized [%T] for iterating over the elements of the array.",
                    iteratorType,
                )
                addModifiers(KModifier.OPERATOR)
                returns(iteratorType)
                addStatement("return %N.iterator()", storage)
            }
        }

    val indices =
        PropertySpec("indices", IntRange::class.asTypeName()) {
            receiver(immutableArrayType)
            getter { addStatement("return %N.indices", storage) }
        }

    val lastIndex =
        PropertySpec("lastIndex", INT) {
            receiver(immutableArrayType)
            getter { addStatement("return %N.lastIndex", storage) }
        }

    val asImmutableArray =
        FunSpec("asImmutableArray") {
            addAnnotation(DELICATE_API_ANNOTATION)
            receiver(arrayType)
            returns(immutableArrayType)
            addStatement("return %T(this)", immutableArrayType)
        }

    val asMutableArray =
        FunSpec("asMutableArray") {
            addAnnotation(DELICATE_API_ANNOTATION)
            receiver(immutableArrayType)
            returns(arrayType)
            addStatement("return %N", storage)
        }

    val toImmutableArray =
        FunSpec("toImmutableArray") {
            receiver(arrayType)
            returns(immutableArrayType)
            addStatement("return %T(copyOf())", immutableArrayType)
        }

    val toMutableArray =
        FunSpec("toMutableArray") {
            receiver(immutableArrayType)
            returns(arrayType)
            addStatement("return %N.copyOf()", storage)
        }

    val asList =
        FunSpec("asList") {
            receiver(immutableArrayType)
            returns(LIST.parameterizedBy(elementType))
            addStatement("return %N.asList()", storage)
        }

    val contains =
        FunSpec("contains") {
            addModifiers(KModifier.OPERATOR)
            receiver(immutableArrayType)
            val element = parameter("element", elementType)
            returns(BOOLEAN)
            addStatement("return %N.contains(%N)", storage, element)
        }

    val contentEquals =
        FunSpec("contentEquals") {
            addModifiers(KModifier.INFIX)
            receiver(immutableArrayType.copy(nullable = true))
            val other = parameter("other", immutableArrayType.copy(nullable = true))
            returns(BOOLEAN)
            addStatement("return this?.%N.contentEquals(%N?.%N)", storage, other, storage)
        }

    val contentHashCode =
        FunSpec("contentHashCode") {
            receiver(immutableArrayType.copy(nullable = true))
            returns(INT)
            addStatement("return this?.%N.contentHashCode()", storage)
        }

    val contentToString =
        FunSpec("contentToString") {
            receiver(immutableArrayType.copy(nullable = true))
            returns(STRING)
            addStatement("return this?.%N.contentToString()", storage)
        }

    val copyInto =
        FunSpec("copyInto") {
            receiver(immutableArrayType)
            val destination = parameter("destination", arrayType)
            val destinationOffset = parameter("destinationOffset", INT) { defaultValue("0") }
            val startIndex = parameter("startIndex", INT) { defaultValue("0") }
            val endIndex = parameter("endIndex", INT) { defaultValue("%N", size) }
            returns(arrayType)
            addStatement(
                "return %N.copyInto(%N, %N, %N, %N)",
                storage,
                destination,
                destinationOffset,
                startIndex,
                endIndex,
            )
        }

    val copyOf =
        FunSpec("copyOf") {
            receiver(immutableArrayType)
            returns(immutableArrayType)
            addStatement("return %T(%N.copyOf())", immutableArrayType, storage)
        }

    val copyOfNewSize =
        FunSpec("copyOf") {
            receiver(immutableArrayType)
            val newSize = parameter("newSize", INT)
            returns(immutableArrayType)
            addStatement("return %T(%N.copyOf(%N))", immutableArrayType, storage, newSize)
        }

    val copyOfRange =
        FunSpec("copyOfRange") {
            receiver(immutableArrayType)
            val fromIndex = parameter("fromIndex", INT)
            val toIndex = parameter("toIndex", INT)
            returns(immutableArrayType)
            addStatement(
                "return %T(%N.copyOfRange(%N, %N))",
                immutableArrayType,
                storage,
                fromIndex,
                toIndex,
            )
        }

    val indexOf =
        FunSpec("indexOf") {
            receiver(immutableArrayType)
            val element = parameter("element", elementType)
            returns(INT)
            addStatement("return %N.indexOf(%N)", storage, element)
        }

    val isEmpty =
        FunSpec("isEmpty") {
            receiver(immutableArrayType)
            returns(BOOLEAN)
            addStatement("return %N.isEmpty()", storage)
        }

    val isNotEmpty =
        FunSpec("isNotEmpty") {
            receiver(immutableArrayType)
            returns(BOOLEAN)
            addStatement("return %N.isNotEmpty()", storage)
        }

    val lastIndexOf =
        FunSpec("lastIndexOf") {
            receiver(immutableArrayType)
            val element = parameter("element", elementType)
            returns(INT)
            addStatement("return %N.lastIndexOf(%N)", storage, element)
        }

    val plusElement =
        FunSpec("plus") {
            addModifiers(KModifier.OPERATOR)
            receiver(immutableArrayType)
            val element = parameter("element", elementType)
            returns(immutableArrayType)
            addStatement("return %T(%N.plus(%N))", immutableArrayType, storage, element)
        }

    val plusElementsArray =
        FunSpec("plus") {
            addModifiers(KModifier.OPERATOR)
            receiver(immutableArrayType)
            val elements = parameter("elements", arrayType)
            returns(immutableArrayType)
            addStatement("return %T(%N.plus(%N))", immutableArrayType, storage, elements)
        }

    val plusElementsImmutableArray =
        FunSpec("plus") {
            addModifiers(KModifier.OPERATOR)
            receiver(immutableArrayType)
            val elements = parameter("elements", immutableArrayType)
            returns(immutableArrayType)
            addStatement(
                "return %T(%N.plus(%N.%N))",
                immutableArrayType,
                storage,
                elements,
                storage,
            )
        }

    val plusElementsCollection =
        FunSpec("plus") {
            addModifiers(KModifier.OPERATOR)
            receiver(immutableArrayType)
            val elements = parameter("elements", COLLECTION.parameterizedBy(elementType))
            returns(immutableArrayType)
            addStatement("return %T(%N.plus(%N))", immutableArrayType, storage, elements)
        }

    val reversedArray =
        FunSpec("reversedArray") {
            receiver(immutableArrayType)
            returns(immutableArrayType)
            addStatement("return %T(%N.reversedArray())", immutableArrayType, storage)
        }

    val sliceArrayCollection =
        FunSpec("sliceArray") {
            receiver(immutableArrayType)
            val indices = parameter("indices", COLLECTION.parameterizedBy(INT))
            returns(immutableArrayType)
            addStatement("return %T(%N.sliceArray(%N))", immutableArrayType, storage, indices)
        }

    val sliceArrayRange =
        FunSpec("sliceArray") {
            receiver(immutableArrayType)
            val indices = parameter("indices", IntRange::class.asTypeName())
            returns(immutableArrayType)
            addStatement("return %T(%N.sliceArray(%N))", immutableArrayType, storage, indices)
        }

    val sortedArray =
        FunSpec("sortedArray") {
            receiver(immutableArrayType)
            returns(immutableArrayType)
            addStatement("return %T(%N.sortedArray())", immutableArrayType, storage)
        }

    val sortedArrayDescending =
        FunSpec("sortedArrayDescending") {
            receiver(immutableArrayType)
            returns(immutableArrayType)
            addStatement("return %T(%N.sortedArrayDescending())", immutableArrayType, storage)
        }

    val file =
        FileSpec(immutableArrayType) {
            addFileComment(
                """
                |
                |NOTE: This file is auto-generated and may not be edited manually.
                |
                """
                    .trimMargin()
            )

            addType(immutableArray)

            addProperty(indices)
            addProperty(lastIndex)

            addFunction(asImmutableArray)
            addFunction(asMutableArray)
            addFunction(toImmutableArray)
            addFunction(toMutableArray)
            addFunction(asList)

            if (elementType !in FLOATING_POINT_TYPES) {
                addFunction(contains)
                addFunction(indexOf)
                addFunction(lastIndexOf)
            }
            addFunction(contentEquals)
            addFunction(contentHashCode)
            addFunction(contentToString)
            addFunction(copyInto)
            addFunction(copyOf)
            addFunction(copyOfNewSize)
            addFunction(copyOfRange)
            addFunction(isEmpty)
            addFunction(isNotEmpty)
            addFunction(plusElement)
            addFunction(plusElementsArray)
            addFunction(plusElementsImmutableArray)
            addFunction(plusElementsCollection)
            addFunction(reversedArray)
            addFunction(sliceArrayCollection)
            addFunction(sliceArrayRange)
            if (elementType in SORTABLE_TYPES) {
                addFunction(sortedArray)
                addFunction(sortedArrayDescending)
            }
        }
}
