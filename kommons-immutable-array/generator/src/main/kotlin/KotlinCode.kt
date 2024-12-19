import java.nio.file.Path
import java.util.*
import kotlin.io.path.createDirectories
import kotlin.io.path.div
import kotlin.io.path.writeText
import org.intellij.lang.annotations.Language

class KotlinCode(val text: String) {
    val imports = mutableSetOf<String>()

    fun import(import: String) {
        imports += import
    }
}

fun kotlinCode(@Language("kotlin") text: String) = KotlinCode(text)

fun kotlinCode(@Language("kotlin") text: String, action: KotlinCode.() -> Unit) =
    KotlinCode(text).apply(action)

class KotlinFile(
    val fileName: String,
    val generatorName: String,
    val suppressions: SortedSet<String>,
    val packageName: String,
    val content: List<KotlinCode>,
) {
    @Language("kotlin")
    override fun toString() = buildString {
        appendLine(
            """
            /*
             * The source code in this file is auto-generated, do not edit manually.
             * Generator: $generatorName
             */
            """
                .trimIndent()
        )

        if (suppressions.isNotEmpty()) {
            appendLine()
            val suppressions = suppressions.joinToString { "\"$it\"" }
            appendLine("@file:Suppress($suppressions)")
        }

        appendLine()
        appendLine("package $packageName")

        val imports = sortedSetOf<String>()
        content.flatMapTo(imports) { it.imports }
        for (import in imports) {
            appendLine("\nimport $import")
        }

        for (code in content) {
            appendLine("\n${code.text}")
        }
    }

    fun writeTo(sourcesRootDir: Path) {
        val packageDir = sourcesRootDir / packageName.replace('.', '/')
        val filePath = packageDir / fileName

        packageDir.createDirectories()
        filePath.writeText(toString())
    }
}
