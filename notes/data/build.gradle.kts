plugins {
    alias(libs.plugins.notemark.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.notemark.jvm.ktor)
    alias(libs.plugins.notemark.android.room)
}

android {
    namespace = "com.ssk.notes.data"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.serialization)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}