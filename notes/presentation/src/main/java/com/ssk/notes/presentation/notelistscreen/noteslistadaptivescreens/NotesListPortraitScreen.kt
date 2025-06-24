package com.ssk.notes.presentation.notelistscreen.noteslistadaptivescreens

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.core.domain.notes.Note
import com.ssk.core.presentation.designsystem.components.NoteMarkFab
import com.ssk.core.presentation.designsystem.components.NoteMarkScaffold
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.notes.presentation.notelistscreen.components.NoteCard
import com.ssk.notes.presentation.notelistscreen.components.NotesListTopBar
import com.ssk.notes.presentation.notelistscreen.handler.NotesListAction
import com.ssk.notes.presentation.notelistscreen.handler.NotesListState

@Composable
fun NotesListPortraitScreen(
    modifier: Modifier = Modifier,
    notesListState: NotesListState,
    onAction: (NotesListAction) -> Unit
) {
    NoteMarkScaffold(
        modifier = modifier,
        topBar = {
            NotesListTopBar(notesListState = notesListState)
        },
        floatingActionButton = {
            NoteMarkFab(
                onClick = {
                    onAction(NotesListAction.OnAddNoteClicked)
                },
                modifier = Modifier
                    .padding(end = 12.dp, bottom = 12.dp)
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSurface)
                .padding(it)
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalItemSpacing = 8.dp
            ) {
                items(
                    items = notesListState.notes,
                    key = { it.id }
                ) { note ->
                    NoteCard(
                        createdAt = note.createdAt,
                        title = note.title,
                        content = note.content,
                        modifier = Modifier.combinedClickable(
                            onClick = { onAction(NotesListAction.OnNoteClicked(note)) },
                            onLongClick = { onAction(NotesListAction.OnLongPressNote(note.id)) }
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListScreenPreview() {
    NoteMarkTheme {
        NotesListPortraitScreen(
            notesListState = NotesListState(
                userInitials = "SK",
                notes = listOf(
                    Note(
                        id = "1",
                        title = "title",
                        content = "Lets build beautiful apps with compose fsfaf afafswgwsgb afastfgwrgvgwrfdgq fsafafaf afwafafvqaeFVQWEFVCWADVFCWWSFDVS  ASFWSAV SDFWSFGBVWSFB V",
                        createdAt = "2025-06-18T13:55:01.503656Z",
                        lastEditedAt = "2025-06-18T13:55:01.503656Z"
                    ),
                    Note(
                        id = "2",
                        title = "title",
                        content = "Lets build beautiful apps with compose fsfaf afafswgwsgb afastfgwrgvgwrfdgq fsafafaf afwafafvqaeFVQWEFVCWADVFCWWSFDVS  ASFWSAV SDFWSFGBVWSFB V",
                        createdAt = "2025-06-18T13:55:01.503656Z",
                        lastEditedAt = "2025-06-18T13:55:01.503656Z"
                    ),
                ),
            ),
            onAction = {}
        )
    }
}