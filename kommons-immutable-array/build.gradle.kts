plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("maven-publish")

    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.ktfmt)
}

kotlin {
    jvm()
    macosArm64()
    linuxX64()
    mingwX64()

    explicitApi()
    compilerOptions {
        extraWarnings = true
        progressiveMode = true
    }

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

ktfmt {
    kotlinLangStyle()
}
