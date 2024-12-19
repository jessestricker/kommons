plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("maven-publish")

    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
}

val generatedSourcesDir = file("src/commonMain/generated")

tasks.register("generateImmutableArrays") {
    group = "generation"
    dependsOn(
        project("generator").tasks.named("run", JavaExec::class) {
            args(generatedSourcesDir)
        },
    )
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
                srcDir(generatedSourcesDir)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

detekt {
    source.setFrom(kotlin.sourceSets.map { it.kotlin })
}

spotless {
    kotlin {
        target(kotlin.sourceSets.map { it.kotlin })
        ktfmt(libs.versions.ktfmt.get()).kotlinlangStyle()
    }
}
