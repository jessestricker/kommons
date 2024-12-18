package kommons.buildsrc.codegen

import java.io.File
import kotlin.reflect.KClass

data class KotlinFileContent(
    val generatedBy: KClass<*>?,
    val packageName: String?,
    val imports: List<String>,
    val declarations: List<String>,
) {
    class Builder {
        internal val imports = mutableListOf<String>()
        internal val declarations = mutableListOf<String>()

        var generatedBy: KClass<*>? = null
        var packageName: String? = null

        fun import(import: String) {
            imports += import
        }

        fun declare(declaration: String) {
            declarations += declaration
        }
    }
}

typealias KotlinFileContentBuildAction = KotlinFileContent.Builder.() -> Unit

fun buildKotlinFileContent(action: KotlinFileContentBuildAction): KotlinFileContent {
    val builder = KotlinFileContent.Builder().apply(action)
    return with(builder) {
        KotlinFileContent(
            generatedBy = generatedBy,
            packageName = packageName,
            imports = imports,
            declarations = declarations,
        )
    }
}

// language=kotlin
val KotlinFileContent.generatedByComment: String?
    get() =
        generatedBy?.let {
            """
        /*
         * The source code in this file is auto-generated, do not edit manually.
         * Generator: ${it.qualifiedName}
         */
        """
                .trimIndent()
        }

val KotlinFileContent.packageDirectoryName: String?
    get() = packageName?.replace('.', '/')

fun KotlinFileContent.code(): String = buildString {
    generatedByComment?.let {
        appendLine(it)
        appendLine()
    }

    appendLine("package $packageName")
    appendLine()

    if (imports.isNotEmpty()) {
        appendLine(imports.joinToString("\n") { "import $it" })
        appendLine()
    }

    appendLine(declarations.joinToString("\n\n"))
}

fun KotlinFileContent.writeTo(sourcesRootDir: File, fileName: String) {
    val packageDirectory =
        packageDirectoryName?.let { sourcesRootDir.resolve(it) } ?: sourcesRootDir
    val kotlinFile = packageDirectory.resolve(fileName)
    val kotlinCode = this.code()

    packageDirectory.mkdirs()
    kotlinFile.writeText(kotlinCode)
}
