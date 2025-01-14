plugins {
    id("kommons.kotlin-library")
}

val generatorSourceSet = sourceSets.register("generator")

tasks.register<JavaExec>("generate") {
    description = "Runs the code generator."
    group = "generation"

    classpath = generatorSourceSet.get().runtimeClasspath
    mainClass = "Generator"
    args = listOf(
        file("src/main/kotlin").toString(), // source directory
        "kommons.arrays", // package name
    )
}
