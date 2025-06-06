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
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}