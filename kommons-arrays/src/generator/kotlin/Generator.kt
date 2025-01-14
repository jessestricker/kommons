import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.div
import kotlin.io.path.writeText
import org.intellij.lang.annotations.Language

class Generator(private val sourceDirectory: Path, private val packageName: String) {
    private val packageDirectory: Path = sourceDirectory / packageName.replace('.', '/')

    @Language("kotlin")
    private fun fileHeader(): String =
        """
        /*
         * This file is auto-generated, do not edit!
         */
        
        @file:Suppress("TooManyFunctions")
        
        package $packageName
        """
            .trimIndent()

    fun generate() {
        packageDirectory.createDirectories()

        for (primitiveType in PrimitiveType.entries) {
            val code = ImmutableArrayCode(primitiveType)
            val fileElements = listOf(fileHeader(), code.toString())
            val filePath = packageDirectory / (code.immutableArrayType + ".kt")
            filePath.writeText(
                fileElements.joinToString(separator = "\n") { it.ensureEndsWithNewline() }
            )
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val generator = Generator(sourceDirectory = Path(args[0]), packageName = args[1])
            generator.generate()
        }
    }
}
