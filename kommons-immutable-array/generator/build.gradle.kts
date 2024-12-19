plugins {
    alias(libs.plugins.kotlin.jvm)
    application

    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

kotlin {
    compilerOptions {
        extraWarnings = true
        progressiveMode = true
    }
}

application {
    mainClass = "GeneratorKt"
}

spotless {
    kotlin {
        ktfmt(libs.versions.ktfmt.get()).kotlinlangStyle()
    }
}
