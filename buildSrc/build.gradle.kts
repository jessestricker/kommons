plugins {
    `kotlin-dsl`

    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

spotless {
    kotlin {
        ktfmt(libs.versions.ktfmt.get()).kotlinlangStyle()
    }
}
