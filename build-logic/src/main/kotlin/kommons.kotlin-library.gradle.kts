plugins {
    id("org.jetbrains.kotlin.jvm")

    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    id("com.ncorti.ktfmt.gradle")
}

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()
    compilerOptions {
        extraWarnings = true
        progressiveMode = true
    }
    jvmToolchain {
        languageVersion = libs.versions.java.map { JavaLanguageVersion.of(it) }
    }
}

testing {
    @Suppress("UnstableApiUsage")
    suites.named<JvmTestSuite>("test") {
        useKotlinTest(libs.versions.kotlin)
    }
}

detekt {
    config.setFrom(rootProject.file(".config/detekt.yml"))
    buildUponDefaultConfig = true
}

ktfmt {
    kotlinLangStyle()
}
