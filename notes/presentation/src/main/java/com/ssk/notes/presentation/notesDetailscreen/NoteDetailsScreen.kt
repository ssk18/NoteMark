package com.ssk.notes.presentation.notesDetailscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.designsystem.theme.SetStatusBarIconsColor
import com.ssk.core.presentation.ui.ObserveAsEvents
import com.ssk.notes.presentation.R
import com.ssk.notes.presentation.notesDetailscreen.components.NoteDetailsAlertDialog
import com.ssk.notes.presentation.notesDetailscreen.components.ViewMode
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailState
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailsAction
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailsEvent
import com.ssk.notes.presentation.notesDetailscreen.notesadaptivescreens.NoteDetailsEditPortraitScreen
import com.ssk.notes.presentation.notesDetailscreen.notesadaptivescreens.NoteDetailsViewScreen
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
                viewModel.onAction(NoteDetailsAction.OnDismissDialog)
                navigateToNotesList()
            }
        )
    }

    when(state.noteMode) {
        ViewMode.EDIT -> NoteDetailsEditPortraitScreen(
            modifier = modifier,
            noteDetailState = state,
            onAction = viewModel::onAction
        )

        ViewMode.VIEW -> {
            NoteDetailsViewScreen(
                modifier = modifier,
                state = state,
                onAction = viewModel::onAction
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsScreenPreview() {
    NoteMarkTheme {
        NoteDetailsEditPortraitScreen(
            noteDetailState = NoteDetailState(
                title = "Title",
                content = "aarfwqt dfafqvcwasw farfwacasvcw faqfafcasf"
            ),
            onAction = {},
        )
    }
}