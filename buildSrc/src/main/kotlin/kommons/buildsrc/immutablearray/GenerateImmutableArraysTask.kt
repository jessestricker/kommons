package kommons.buildsrc.immutablearray

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

abstract class GenerateImmutableArraysTask : DefaultTask() {
    init {
        group = "generation"
        description = "Generates the source code for the immutable arrays."
    }

    @get:OutputDirectory abstract val outputDirectory: DirectoryProperty

    @TaskAction
    fun generate() {
        val outputDirectory = outputDirectory.asFile.get()
        ImmutableArraysGenerator.generate(outputDirectory)
    }
}
