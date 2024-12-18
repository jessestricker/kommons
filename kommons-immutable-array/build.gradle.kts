import kommons.buildsrc.immutablearray.GenerateImmutableArraysTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("maven-publish")

    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
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
        commonMain {
            kotlin {
                val generateImmutableArrays by tasks.registering(GenerateImmutableArraysTask::class) {
                    outputDirectory.assign(file("src/commonMain/generated"))
                }
                srcDir(generateImmutableArrays)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

spotless {
    kotlin {
        target(kotlin.sourceSets.map { it.kotlin })
        ktfmt(libs.versions.ktfmt.get()).kotlinlangStyle()
    }
}
