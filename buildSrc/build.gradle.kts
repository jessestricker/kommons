plugins {
    `kotlin-dsl`

    alias(libs.plugins.spotless)
}

spotless {
    kotlin {
        target(kotlin.sourceSets.map { it.kotlin })
        ktfmt(libs.versions.ktfmt.get()).kotlinlangStyle()
    }
}
