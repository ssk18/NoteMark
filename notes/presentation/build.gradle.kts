plugins {
    alias(libs.plugins.notemark.android.feature.ui)
}

android {
    namespace = "com.ssk.notes.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.notes.domain)
    implementation(projects.auth.domain)
    implementation(libs.timber)
}