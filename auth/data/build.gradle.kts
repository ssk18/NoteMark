plugins {
    alias(libs.plugins.notemark.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.notemark.jvm.ktor)
}

android {
    namespace = "com.ssk.auth.data"
}

dependencies {
    implementation(project(":auth:domain"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(libs.bundles.koin)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.serialization)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}