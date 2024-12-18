import com.diffplug.gradle.spotless.KotlinExtension
import kommons.buildsrc.immutablearray.GenerateImmutableArraysTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("maven-publish")

    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
}

val generateImmutableArrays by tasks.creating(GenerateImmutableArraysTask::class) {
    outputDirectory.assign(file("src/commonMain/generated"))
    finalizedBy("spotlessGeneratedApply")
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
        targetExclude(fileTree(generateImmutableArrays.outputDirectory))

        ktfmt(libs.versions.ktfmt.get()).kotlinlangStyle()
    }
    format("generated", KotlinExtension::class.java) {
        target(generateImmutableArrays.outputDirectory)

        ktfmt(libs.versions.ktfmt.get()).kotlinlangStyle()
    }
}
