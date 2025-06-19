package com.ssk.notes.presentation.notelistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.core.domain.notes.Note
import com.ssk.core.presentation.designsystem.components.NoteMarkFab
import com.ssk.core.presentation.designsystem.components.NoteMarkScaffold
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.designsystem.theme.SetStatusBarIconsColor
import com.ssk.core.presentation.ui.ObserveAsEvents
import com.ssk.notes.presentation.notelistscreen.components.NoteCard
import com.ssk.notes.presentation.notelistscreen.components.NotesListTopBar
import com.ssk.notes.presentation.notelistscreen.handler.NotesListAction
import com.ssk.notes.presentation.notelistscreen.handler.NotesListEvents
import com.ssk.notes.presentation.notelistscreen.handler.NotesListState
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: NotesListViewModel = koinViewModel(),
    navigateToNoteDetail: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    SetStatusBarIconsColor(true)

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            is NotesListEvents.NavigateToNoteDetail -> navigateToNoteDetail(event.noteId)
            is NotesListEvents.ShowError -> {
                snackBarHostState.showSnackbar(
                    message = event.error.asString(context)
                )
            }
        }
    }

    NotesListScreen(
        modifier = modifier,
        notesListState = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun NotesListScreen(
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
                modifier = Modifier.fillMaxSize().padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalItemSpacing = 12.dp
            ) {
                items(
                    items = notesListState.notes,
                    key = { it.id }
                ) { note ->
                    NoteCard(
                        createdAt = note.createdAt,
                        title = note.title,
                        content = note.content
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
        NotesListScreen(
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