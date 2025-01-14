plugins {
    id("kommons.kotlin-library")
}

val generatedSourceDirectory = file("src/main/generated/kotlin")

sourceSets.main {
    kotlin.srcDir(generatedSourceDirectory)
}

val generatorSourceSet = sourceSets.register("generator")

tasks.register<JavaExec>("generate") {
    description = "Runs the code generator."
    group = "generation"

    classpath = generatorSourceSet.get().runtimeClasspath
    mainClass = "Generator"
    args = listOf(
        generatedSourceDirectory.toString(), // source directory
        "kommons.arrays", // package name
    )
}
