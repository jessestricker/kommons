plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.plugins.kotlin.jvm.asDependency())

    implementation(libs.plugins.detekt.asDependency())
    implementation(libs.plugins.dokka.asDependency())
    implementation(libs.plugins.ktfmt.asDependency())

    // https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

// https://github.com/gradle/gradle/issues/17963#issuecomment-939207895
fun Provider<PluginDependency>.asDependency() =
    this.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }
