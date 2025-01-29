plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "kommons"

includeBuild("build-logic")

include(
    "kommons-arrays",
    "kommons-hash",
)
