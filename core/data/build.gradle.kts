plugins {
    alias(libs.plugins.notemark.android.library)
    alias(libs.plugins.notemark.jvm.ktor)
    alias(libs.plugins.notemark.android.room)
}

android {
    namespace = "com.ssk.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.timber)
    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preference)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
}