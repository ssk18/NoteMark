package com.ssk.notes.presentation.notesDetailscreen.notesadaptivescreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.core.presentation.designsystem.components.NoteMarkScaffold
import com.ssk.core.presentation.designsystem.expandWidth
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.notes.presentation.notesDetailscreen.components.ExtendedFab
import com.ssk.notes.presentation.notesDetailscreen.components.NoteDetailsTopBar
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailState
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailsAction

@Composable
fun NoteDetailsPortraitScreen(
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
        },
        floatingActionButton = {
            ExtendedFab(
                onEditClick = {
                    onAction(NoteDetailsAction.OnEditNoteClicked(noteDetailState.noteMode))
                },
                onViewClick = {
                    onAction(NoteDetailsAction.OnReadNoteClicked)
                },
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.onPrimary
                )
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = noteDetailState.title,
                onValueChange = {
                    onAction(NoteDetailsAction.OnTitleChanged(it))
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.titleLarge,
                placeholder = {
                    Text(
                        text = "Note Title",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
            HorizontalDivider(
                modifier = Modifier
                    .expandWidth(16.dp),
                color = MaterialTheme.colorScheme.surface
            )
            TextField(
                value = noteDetailState.content,
                onValueChange = {
                    onAction(NoteDetailsAction.OnContentChanged(it))
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.bodyLarge,
                placeholder = {
                    Text(
                        text = "Tap to enter note content",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsPortraitScreenPreview() {
    NoteMarkTheme {
        NoteDetailsPortraitScreen(
            noteDetailState = NoteDetailState(
                title = "Title",
                content = "aarfwqt dfafqvcwasw farfwacasvcw faqfafcasf"
            ),
            onAction = {}
        )
    }
}