package com.ssk.notes.presentation.notesDetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.core.presentation.designsystem.components.NoteMarkScaffold
import com.ssk.core.presentation.designsystem.expandWidth
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.notes.presentation.notesDetailscreen.components.NoteDetailsTopBar
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailState
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailsAction

@Composable
fun NoteDetailsScreen(
    modifier: Modifier = Modifier,
    noteDetailState: NoteDetailState,
    onAction: (NoteDetailsAction) -> Unit
) {
    NoteMarkScaffold(
        modifier = modifier,
        topBar = {
            NoteDetailsTopBar(
                onCloseNoteClicked = {
                    onAction(NoteDetailsAction.OnCloseNoteClicked)
                },
                onSaveNoteClicked = {
                    onAction(NoteDetailsAction.OnNoteSaved)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.onPrimary
                )
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = noteDetailState.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            HorizontalDivider(
                modifier = Modifier
                    .expandWidth(16.dp),
                color = MaterialTheme.colorScheme.surface
            )
            Text(
                text = noteDetailState.content,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsScreenPreview() {
    NoteMarkTheme {
        NoteDetailsScreen(
            noteDetailState = NoteDetailState(
                title = "Title",
                content = "aarfwqt dfafqvcwasw farfwacasvcw faqfafcasf"
            ),
            onAction = {},
        )
    }
}