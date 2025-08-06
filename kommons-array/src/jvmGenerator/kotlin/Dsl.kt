import com.squareup.kotlinpoet.*

// file

inline fun FileSpec(name: ClassName, block: FileSpec.Builder.() -> Unit = {}): FileSpec =
    FileSpec.builder(name).apply(block).build()

// annotation

inline fun AnnotationSpec(
    type: ClassName,
    block: AnnotationSpec.Builder.() -> Unit = {},
): AnnotationSpec = AnnotationSpec.builder(type).apply(block).build()

// type

inline fun ClassSpec(name: ClassName, block: TypeSpec.Builder.() -> Unit = {}): TypeSpec =
    TypeSpec.classBuilder(name).apply(block).build()

inline fun TypeSpec.Builder.primaryConstructor(block: FunSpec.Builder.() -> Unit = {}): FunSpec =
    ConstructorSpec(block).also { primaryConstructor(it) }

inline fun TypeSpec.Builder.secondaryConstructor(block: FunSpec.Builder.() -> Unit = {}): FunSpec =
    ConstructorSpec(block).also { addFunction(it) }

inline fun TypeSpec.Builder.property(
    name: String,
    type: TypeName,
    block: PropertySpec.Builder.() -> Unit = {},
): PropertySpec = PropertySpec(name, type, block).also { addProperty(it) }

inline fun TypeSpec.Builder.function(
    name: String,
    block: FunSpec.Builder.() -> Unit = {},
): FunSpec = FunSpec(name, block).also { addFunction(it) }

// property

inline fun PropertySpec(
    name: String,
    type: TypeName,
    block: PropertySpec.Builder.() -> Unit = {},
): PropertySpec = PropertySpec.builder(name, type).apply(block).build()

inline fun PropertySpec.Builder.getter(block: FunSpec.Builder.() -> Unit = {}): FunSpec =
    FunSpec.getterBuilder().apply(block).build().also { getter(it) }

// function

inline fun FunSpec(name: String, block: FunSpec.Builder.() -> Unit = {}): FunSpec =
    FunSpec.builder(name).apply(block).build()

inline fun ConstructorSpec(block: FunSpec.Builder.() -> Unit = {}): FunSpec =
    FunSpec.constructorBuilder().apply(block).build()

inline fun FunSpec.Builder.parameter(
    name: String,
    type: TypeName,
    block: ParameterSpec.Builder.() -> Unit = {},
): ParameterSpec = ParameterSpec(name, type, block).also { addParameter(it) }

// parameter

inline fun ParameterSpec(
    name: String,
    type: TypeName,
    block: ParameterSpec.Builder.() -> Unit = {},
): ParameterSpec = ParameterSpec.builder(name, type).apply(block).build()

// lambda type

fun lambdaTypeOf(parameters: List<TypeName>, returnType: TypeName): LambdaTypeName =
    LambdaTypeName.get(
        receiver = null,
        parameters = parameters.map { ParameterSpec.unnamed(it) },
        returnType = returnType,
    )
