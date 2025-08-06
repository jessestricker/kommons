import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlin.io.path.Path

fun main(args: Array<String>) {
    val outputDir = Path(args[0])
    for (generator in GENERATORS) {
        generator.file.writeTo(outputDir)
    }
}

const val PACKAGE_NAME = "de.jessestricker.kommons.array"
val DELICATE_API_ANNOTATION = ClassName(PACKAGE_NAME, "DelicateKommonsArrayApi")

val SORTABLE_TYPES = setOf(BYTE, SHORT, INT, LONG, CHAR, FLOAT, DOUBLE)
val FLOATING_POINT_TYPES = setOf(FLOAT, DOUBLE)
val UNSIGNED_TYPES = setOf(U_BYTE, U_SHORT, U_INT, U_LONG)
val PRIMITIVE_TYPES = setOf(BOOLEAN, BYTE, SHORT, INT, LONG, CHAR, FLOAT, DOUBLE)

fun arrayTypeOf(elementType: ClassName) = elementType.peerClass("${elementType.simpleName}Array")

fun immutableArrayTypeOf(elementType: ClassName) =
    ClassName(PACKAGE_NAME, "Immutable${elementType.simpleName}Array")

fun iteratorTypeOf(elementType: ClassName) =
    if (elementType in PRIMITIVE_TYPES)
        Iterator::class.asClassName().peerClass("${elementType.simpleName}Iterator")
    else Iterator::class.asClassName().parameterizedBy(elementType)

fun signedTypeOf(unsignedType: ClassName) =
    unsignedType.takeIf { it in UNSIGNED_TYPES }?.peerClass(unsignedType.simpleName.drop(1))

fun suppress(vararg names: String) =
    AnnotationSpec(Suppress::class.asTypeName()) { addMember("%S", *names) }

class Generator(val elementType: ClassName, val elementPluralName: String) {
    val baseAnnotations =
        if (elementType in UNSIGNED_TYPES)
            listOf(AnnotationSpec(ExperimentalUnsignedTypes::class.asTypeName()))
        else emptyList()

    val arrayType = arrayTypeOf(elementType)
    val iteratorType = iteratorTypeOf(elementType)
    val immutableArrayType = immutableArrayTypeOf(elementType)

    val storage = MemberName(immutableArrayType, "storage")
    val size = MemberName(immutableArrayType, "size")

    val immutableArray =
        ClassSpec(immutableArrayType) {
            addKdoc("An immutable array of %L.", elementPluralName)
            addAnnotations(baseAnnotations)
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
                addAnnotation(suppress("detekt:FunctionNaming"))
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
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            getter { addStatement("return %N.indices", storage) }
        }

    val lastIndex =
        PropertySpec("lastIndex", INT) {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            getter { addStatement("return %N.lastIndex", storage) }
        }

    val asImmutableArray =
        FunSpec("asImmutableArray") {
            addAnnotations(baseAnnotations)
            addAnnotation(DELICATE_API_ANNOTATION)
            receiver(arrayType)
            returns(immutableArrayType)
            addStatement("return %T(this)", immutableArrayType)
        }

    val asMutableArray =
        FunSpec("asMutableArray") {
            addAnnotations(baseAnnotations)
            addAnnotation(DELICATE_API_ANNOTATION)
            receiver(immutableArrayType)
            returns(arrayType)
            addStatement("return %N", storage)
        }

    val toImmutableArray =
        FunSpec("toImmutableArray") {
            addAnnotations(baseAnnotations)
            receiver(arrayType)
            returns(immutableArrayType)
            addStatement("return %T(copyOf())", immutableArrayType)
        }

    val toMutableArray =
        FunSpec("toMutableArray") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            returns(arrayType)
            addStatement("return %N.copyOf()", storage)
        }

    val asList =
        FunSpec("asList") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            returns(LIST.parameterizedBy(elementType))
            addStatement("return %N.asList()", storage)
        }

    val asSignedImmutableArray =
        signedTypeOf(elementType)?.let { signedElementType ->
            val signedImmutableArrayType = immutableArrayTypeOf(signedElementType)
            val signedArrayType = arrayTypeOf(signedElementType)

            FunSpec("as${signedImmutableArrayType.simpleName}") {
                addAnnotations(baseAnnotations)
                receiver(immutableArrayType)
                returns(signedImmutableArrayType)
                addStatement(
                    "return %T(%N.%N())",
                    signedImmutableArrayType,
                    storage,
                    "as${signedArrayType.simpleName}",
                )
            }
        }

    val contains =
        FunSpec("contains") {
            addAnnotations(baseAnnotations)
            addModifiers(KModifier.OPERATOR)
            receiver(immutableArrayType)
            val element = parameter("element", elementType)
            returns(BOOLEAN)
            addStatement("return %N.contains(%N)", storage, element)
        }

    val contentEquals =
        FunSpec("contentEquals") {
            addAnnotations(baseAnnotations)
            addModifiers(KModifier.INFIX)
            receiver(immutableArrayType.copy(nullable = true))
            val other = parameter("other", immutableArrayType.copy(nullable = true))
            returns(BOOLEAN)
            addStatement("return this?.%N.contentEquals(%N?.%N)", storage, other, storage)
        }

    val contentHashCode =
        FunSpec("contentHashCode") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType.copy(nullable = true))
            returns(INT)
            addStatement("return this?.%N.contentHashCode()", storage)
        }

    val contentToString =
        FunSpec("contentToString") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType.copy(nullable = true))
            returns(STRING)
            addStatement("return this?.%N.contentToString()", storage)
        }

    val copyInto =
        FunSpec("copyInto") {
            addAnnotations(baseAnnotations)
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
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            returns(immutableArrayType)
            addStatement("return %T(%N.copyOf())", immutableArrayType, storage)
        }

    val copyOfNewSize =
        FunSpec("copyOf") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            val newSize = parameter("newSize", INT)
            returns(immutableArrayType)
            addStatement("return %T(%N.copyOf(%N))", immutableArrayType, storage, newSize)
        }

    val copyOfRange =
        FunSpec("copyOfRange") {
            addAnnotations(baseAnnotations)
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
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            val element = parameter("element", elementType)
            returns(INT)
            addStatement("return %N.indexOf(%N)", storage, element)
        }

    val isEmpty =
        FunSpec("isEmpty") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            returns(BOOLEAN)
            addStatement("return %N.isEmpty()", storage)
        }

    val isNotEmpty =
        FunSpec("isNotEmpty") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            returns(BOOLEAN)
            addStatement("return %N.isNotEmpty()", storage)
        }

    val lastIndexOf =
        FunSpec("lastIndexOf") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            val element = parameter("element", elementType)
            returns(INT)
            addStatement("return %N.lastIndexOf(%N)", storage, element)
        }

    val plusElement =
        FunSpec("plus") {
            addAnnotations(baseAnnotations)
            addModifiers(KModifier.OPERATOR)
            receiver(immutableArrayType)
            val element = parameter("element", elementType)
            returns(immutableArrayType)
            addStatement("return %T(%N.plus(%N))", immutableArrayType, storage, element)
        }

    val plusElementsArray =
        FunSpec("plus") {
            addAnnotations(baseAnnotations)
            addModifiers(KModifier.OPERATOR)
            receiver(immutableArrayType)
            val elements = parameter("elements", arrayType)
            returns(immutableArrayType)
            addStatement("return %T(%N.plus(%N))", immutableArrayType, storage, elements)
        }

    val plusElementsImmutableArray =
        FunSpec("plus") {
            addAnnotations(baseAnnotations)
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
            addAnnotations(baseAnnotations)
            addModifiers(KModifier.OPERATOR)
            receiver(immutableArrayType)
            val elements = parameter("elements", COLLECTION.parameterizedBy(elementType))
            returns(immutableArrayType)
            addStatement("return %T(%N.plus(%N))", immutableArrayType, storage, elements)
        }

    val reversedArray =
        FunSpec("reversedArray") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            returns(immutableArrayType)
            addStatement("return %T(%N.reversedArray())", immutableArrayType, storage)
        }

    val sliceArrayCollection =
        FunSpec("sliceArray") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            val indices = parameter("indices", COLLECTION.parameterizedBy(INT))
            returns(immutableArrayType)
            addStatement("return %T(%N.sliceArray(%N))", immutableArrayType, storage, indices)
        }

    val sliceArrayRange =
        FunSpec("sliceArray") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            val indices = parameter("indices", IntRange::class.asTypeName())
            returns(immutableArrayType)
            addStatement("return %T(%N.sliceArray(%N))", immutableArrayType, storage, indices)
        }

    val sortedArray =
        FunSpec("sortedArray") {
            addAnnotations(baseAnnotations)
            receiver(immutableArrayType)
            returns(immutableArrayType)
            addStatement("return %T(%N.sortedArray())", immutableArrayType, storage)
        }

    val sortedArrayDescending =
        FunSpec("sortedArrayDescending") {
            addAnnotations(baseAnnotations)
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
            addAnnotation(suppress("detekt:TooManyFunctions"))

            addType(immutableArray)

            addProperty(indices)
            addProperty(lastIndex)

            addFunction(asImmutableArray)
            addFunction(asMutableArray)
            addFunction(toImmutableArray)
            addFunction(toMutableArray)
            addFunction(asList)
            asSignedImmutableArray?.let { addFunction(it) }

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

val GENERATORS =
    listOf(
        Generator(BOOLEAN, "booleans"),
        Generator(BYTE, "bytes"),
        Generator(SHORT, "shorts"),
        Generator(INT, "ints"),
        Generator(LONG, "longs"),
        Generator(CHAR, "chars"),
        Generator(FLOAT, "floats"),
        Generator(DOUBLE, "doubles"),
        Generator(U_BYTE, "unsigned bytes"),
        Generator(U_SHORT, "unsigned shorts"),
        Generator(U_INT, "unsigned ints"),
        Generator(U_LONG, "unsigned longs"),
    )
