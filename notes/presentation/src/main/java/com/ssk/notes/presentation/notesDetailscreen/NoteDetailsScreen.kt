package com.ssk.notes.presentation.notesDetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.ssk.core.presentation.designsystem.components.NoteMarkScaffold
import com.ssk.core.presentation.designsystem.expandWidth
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.designsystem.theme.SetStatusBarIconsColor
import com.ssk.core.presentation.ui.ObserveAsEvents
import com.ssk.notes.presentation.R
import com.ssk.notes.presentation.notesDetailscreen.components.NoteDetailsAlertDialog
import com.ssk.notes.presentation.notesDetailscreen.components.NoteDetailsTopBar
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailState
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailsAction
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailsEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteDetailsScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: NoteDetailsViewModel = koinViewModel(),
    navigateToNotesList: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SetStatusBarIconsColor(true)

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            NoteDetailsEvent.NavigateToNotesList -> navigateToNotesList()
            is NoteDetailsEvent.ShowError -> TODO()
        }
    }

    if (state.showDialog) {
        NoteDetailsAlertDialog(
            dialogTitle = stringResource(R.string.discard_changes),
            dialogMessage = stringResource(R.string.you_have_unsaved_changes_if_you_discard_now_all_changes_wil_be_lost),
            onDismiss = {
                viewModel.onAction(NoteDetailsAction.OnDismissDialog)
            },
            onConfirm = {
                navigateToNotesList()
            }
        )
    }

    NoteDetailsScreen(
        modifier = modifier,
        noteDetailState = state,
        onAction = viewModel::onAction
    )
}

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
                .padding(horizontal = 16.dp ),
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
                textStyle = MaterialTheme.typography.titleLarge
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