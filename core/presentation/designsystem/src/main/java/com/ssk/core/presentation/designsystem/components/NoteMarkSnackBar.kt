package com.ssk.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ssk.core.presentation.designsystem.theme.NoteMarkGreen

@Composable
fun NoteMarkSnackBar(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState,
    snackBarType: SnackbarType
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
        snackbar = { data ->
            NoteMarkEventBanner(
                text = data.visuals.message,
                snackbarType = snackBarType
            )
        }
    )
}

@Composable
fun NoteMarkEventBanner(
    modifier: Modifier = Modifier,
    text: String,
    snackbarType: SnackbarType
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .addBackgroundColor(snackbarType)
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = addBackgroundContentColor(snackbarType),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Modifier.addBackgroundColor(snackbarType: SnackbarType): Modifier {
    return when (snackbarType) {
        SnackbarType.Success -> this.background(NoteMarkGreen)
        SnackbarType.Error -> this.background(MaterialTheme.colorScheme.error)
    }
}

@Composable
fun BoxScope.addBackgroundContentColor(snackbarType: SnackbarType): androidx.compose.ui.graphics.Color {
    return when (snackbarType) {
        SnackbarType.Success -> MaterialTheme.colorScheme.onPrimary
        SnackbarType.Error -> MaterialTheme.colorScheme.onError
    }
}

enum class SnackbarType {
    Success,
    Error
}