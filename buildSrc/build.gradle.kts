plugins {
    `kotlin-dsl`

    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

spotless {
    kotlin {
        ktfmt().kotlinlangStyle()
    }
}
