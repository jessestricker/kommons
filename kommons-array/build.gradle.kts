import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.kotlin.multiplatform)

    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.ktfmt)
}

kotlin {
    jvm()
    linuxX64()
    macosArm64()
    mingwX64()

    sourceSets.commonTest {
        dependencies {
            implementation(libs.kotlin.test)
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
tasks.withType<Detekt> {
    jvmTarget = "1.8"
}

ktfmt {
    kotlinLangStyle()
}
tasks.ktfmtFormatScripts { enabled = false }
tasks.ktfmtCheckScripts { enabled = false }

kotlin {
    jvm {
        val generator by compilations.creating {
            dependencies {
                implementation(libs.square.kotlinpoet)
            }
        }

        tasks.register<JavaExec>("generatorRun") {
            group = "run"
            mainClass = "MainKt"
            classpath(generator.output)
            classpath(generator.configurations.runtimeDependencyConfiguration)
            args(file("src/commonMain/kotlin"))
        }
    }
}
