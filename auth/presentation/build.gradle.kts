plugins {
    alias(libs.plugins.notemark.android.feature.ui)
}

android {
    namespace = "com.ssk.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
    implementation(libs.timber)
    implementation(libs.material3.adaptive)
}