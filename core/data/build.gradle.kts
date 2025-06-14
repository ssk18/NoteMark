plugins {
    alias(libs.plugins.notemark.android.library)
}

android {
    namespace = "com.ssk.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(projects.core.domain)
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preference)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
}