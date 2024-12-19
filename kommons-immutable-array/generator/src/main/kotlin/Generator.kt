import java.nio.file.Path
import kotlin.io.path.Path

class Generator(val outputDirectory: Path) {
    fun generate() {
        // utility sources
        PrimitiveType.entries
            .map { primitiveType -> Templates(primitiveType).arrayContentEquals() }
            .writeFile("ArrayContentEquals.kt")
        PrimitiveType.entries
            .map { primitiveType -> Templates(primitiveType).arrayContentHashCode() }
            .writeFile("ArrayContentHashCode.kt")

        // immutable arrays
        for (primitiveType in PrimitiveType.entries) {
            val templates = Templates(primitiveType)
            val fileContent =
                listOf(
                    templates.immutableArrayClass(),
                    templates.arrayToImmutableArray(),
                    templates.immutableArrayToArray(),
                    templates.immutableArraySliceArray(),
                    templates.immutableArrayLastIndex(),
                    templates.immutableArrayIndices(),
                    templates.immutableArrayIsEmpty(),
                    templates.immutableArrayIsNotEmpty(),
                    templates.immutableArrayIndexOf(),
                    templates.immutableArrayLastIndexOf(),
                    templates.immutableArrayContains(),
                    templates.immutableArrayAsList(),
                )
            fileContent.writeFile("${templates.immutableArrayType}.kt")
        }
    }

    private fun List<KotlinCode>.writeFile(fileName: String) {
        val file =
            KotlinFile(
                fileName = fileName,
                generatorName = GENERATOR_NAME,
                suppressions = SUPPRESSIONS,
                packageName = PACKAGE_NAME,
                content = this,
            )
        file.writeTo(outputDirectory)
    }
}

private const val GENERATOR_NAME = "kommons-immutable-array/generator/src/main/kotlin/Generator.kt"
private val SUPPRESSIONS =
    sortedSetOf("detekt:MagicNumber", "detekt:ReturnCount", "detekt:TooManyFunctions")
private const val PACKAGE_NAME = "kommons"

fun main(args: Array<String>) {
    val outputDirectory = Path(args[0])
    val generator = Generator(outputDirectory)
    generator.generate()
}
