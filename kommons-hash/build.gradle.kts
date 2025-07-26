plugins {
    alias(libs.plugins.kotlin.multiplatform)
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
