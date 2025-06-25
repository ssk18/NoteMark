package com.ssk.core.presentation.ui

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalScreenOrientation = staticCompositionLocalOf<ScreenOrientation> {
    error("No orientation provided")
}

@Composable
fun ProvideOrientation(content: @Composable () -> Unit) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    val orientation = when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> ScreenOrientation.Portrait
        DeviceConfiguration.MOBILE_LANDSCAPE -> ScreenOrientation.Landscape
        DeviceConfiguration.TABLET_PORTRAIT,
        DeviceConfiguration.TABLET_LANDSCAPE,
        DeviceConfiguration.DESKTOP -> ScreenOrientation.Tablet
    }

    CompositionLocalProvider(LocalScreenOrientation provides orientation) {
        content()
    }
}