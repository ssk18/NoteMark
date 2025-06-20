package com.ssk.notes.presentation.notesDetailscreen.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.notes.presentation.R

@Composable
fun NoteDetailsAlertDialog(
    modifier: Modifier = Modifier,
    dialogTitle: String,
    dialogMessage: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
           TextButton(
               onClick = onConfirm
           ) {
               Text(text = stringResource(R.string.discard))
           }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(text = stringResource(R.string.keep_editing))
            }
        },
        title = {
            Text(
                text = dialogTitle,
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(
                text = dialogMessage,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsAlertDialogPreview() {
    NoteMarkTheme {
        NoteDetailsAlertDialog(
            dialogTitle = "Discard Changes",
            dialogMessage = "Are you sure you want to discard changes?",
            onDismiss = {},
            onConfirm = {}
        )
    }
}
