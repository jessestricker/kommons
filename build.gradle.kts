plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false

    alias(libs.plugins.dokka)
}

dependencies {
    dokka(projects.kommonsArray)
    dokka(projects.kommonsHash)
}
