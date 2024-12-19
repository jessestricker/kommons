rootProject.name = "kommons"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}

include(
    "kommons-immutable-array",
    "kommons-immutable-array:generator",
)
