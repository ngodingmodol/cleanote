plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    ksp(libs.room.compiler)
    implementation(libs.room.common)
    implementation(libs.kotlinx.coroutines.core)
}