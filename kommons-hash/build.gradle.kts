import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.kotlin.multiplatform)

    alias(libs.plugins.detekt)
}

repositories {
    mavenCentral()
}

kotlin {
    jvm()

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }

    explicitApi()
    compilerOptions {
        progressiveMode = true
        extraWarnings = true
    }
}

detekt {
    source.setFrom(kotlin.sourceSets.map { it.kotlin })
}
tasks.withType<Detekt>{
    jvmTarget = "1.8"
}
