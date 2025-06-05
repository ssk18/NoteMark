package com.ssk.core.presentation.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

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
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}