package com.ssk.notes.presentation.notesDetailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.designsystem.theme.NotemarkOnPrimary

@Composable
fun NoteDetailsTopBar(
    modifier: Modifier = Modifier,
    onCloseNoteClicked: () -> Unit,
    onSaveNoteClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(NotemarkOnPrimary)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onCloseNoteClicked
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close note",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                text = "SAVE NOTE",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable {
                        onSaveNoteClicked.invoke()
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsTopBarPreview(){
    NoteMarkTheme {
        NoteDetailsTopBar(
            onCloseNoteClicked = {},
            onSaveNoteClicked = {}
        )
    }
}