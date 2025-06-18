package com.ssk.notes.presentation.notelistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.core.presentation.designsystem.components.NoteMarkFab
import com.ssk.core.presentation.designsystem.components.NoteMarkScaffold
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.designsystem.theme.NotemarkOnPrimary
import com.ssk.core.presentation.designsystem.theme.SetStatusBarIconsColor
import com.ssk.notes.presentation.R
import com.ssk.notes.presentation.notelistscreen.components.UserProfileCard
import com.ssk.notes.presentation.notelistscreen.handler.NotesListAction
import com.ssk.notes.presentation.notelistscreen.handler.NotesListState
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: NotesListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SetStatusBarIconsColor(true)

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
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
            ) {
                Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(NotemarkOnPrimary)
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.notemark),
                        style = MaterialTheme.typography.titleMedium
                    )
                    UserProfileCard(
                        userInitials = notesListState.userInitials,
                    )
                }
            }
        },
        floatingActionButton = {
            NoteMarkFab(
                onClick = {
                    onAction(NotesListAction.OnAddNoteClicked)
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSurface)
                .padding(it)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListScreenPreview() {
    NoteMarkTheme {
        NotesListScreen(
            notesListState = NotesListState(userInitials = "SK"),
            onAction = {}
        )
    }
}