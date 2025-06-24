package com.ssk.notes.presentation.notelistscreen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun NoteDeleteDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    content: String,
) {
    androidx.compose.material3.AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = true,
            decorFitsSystemWindows = true
        ),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        },
        text = {
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = "Confirm",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NoteDeleteDialogPreview() {
    NoteMarkTheme {
        NoteDeleteDialog(
            onDismiss = {},
            onConfirm = {},
            title = "Delete Note",
            content = "Are you sure you want to delete this note?"
        )
    }
}