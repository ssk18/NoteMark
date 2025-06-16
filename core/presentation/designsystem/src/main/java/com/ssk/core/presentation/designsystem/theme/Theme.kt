package com.ssk.core.presentation.designsystem.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = NotemarkPrimary,
    onPrimary = NotemarkOnPrimary,
    surface = NotemarkSurface,
    onSurface = NotemarkOnSurface,
    onSurfaceVariant = NotemarkOnSurfaceVar,
    error = NotemarkError
)

@Composable
fun NoteMarkTheme(
    content: @Composable () -> Unit,
) {
    val colorScheme = LightColorScheme
    SetStatusBarIconsColor(false)
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun SetStatusBarIconsColor(darkIcons: Boolean) {
    val view = LocalView.current

    val insetsController = remember(view) {
        (view.context as? Activity)
            ?.window
            ?.let { WindowCompat.getInsetsController(it, it.decorView) }
    }

    DisposableEffect(true) {
        val originalAppearance = insetsController?.isAppearanceLightStatusBars
        insetsController?.isAppearanceLightStatusBars = darkIcons

        onDispose {
            originalAppearance?.let {
                insetsController.isAppearanceLightStatusBars = it
            }
        }
    }
}