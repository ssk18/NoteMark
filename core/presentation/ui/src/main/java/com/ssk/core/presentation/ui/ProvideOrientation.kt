package com.ssk.core.presentation.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration

val LocalScreenOrientation = staticCompositionLocalOf<ScreenOrientation> {
    error("No orientation provided")
}

@Composable
fun ProvideOrientation(content: @Composable () -> Unit) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    
    // Simple tablet detection based on screen width
    val screenWidthDp = configuration.screenWidthDp
    val isTablet = screenWidthDp >= 600 // 600dp is commonly used threshold for tablets

    val orientation = when {
        isLandscape -> ScreenOrientation.Landscape
        isTablet -> ScreenOrientation.Tablet
        else -> ScreenOrientation.Portrait
    }

    CompositionLocalProvider(LocalScreenOrientation provides orientation) {
        content()
    }
}