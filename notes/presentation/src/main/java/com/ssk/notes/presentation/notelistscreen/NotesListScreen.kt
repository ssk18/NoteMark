package com.ssk.notes.presentation.notelistscreen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.core.presentation.designsystem.theme.SetStatusBarIconsColor
import com.ssk.core.presentation.ui.LocalScreenOrientation
import com.ssk.core.presentation.ui.ObserveAsEvents
import com.ssk.core.presentation.ui.ScreenOrientation
import com.ssk.notes.presentation.R
import com.ssk.notes.presentation.notelistscreen.components.NoteDeleteDialog
import com.ssk.notes.presentation.notelistscreen.handler.NotesListAction
import com.ssk.notes.presentation.notelistscreen.handler.NotesListEvents
import com.ssk.notes.presentation.notelistscreen.handler.NotesListState
import com.ssk.notes.presentation.notelistscreen.noteslistadaptivescreens.NotesListLandscapeScreen
import com.ssk.notes.presentation.notelistscreen.noteslistadaptivescreens.NotesListPortraitScreen
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

    if (state.showDeleteDialog) {
        NoteDeleteDialog(
            title = stringResource(R.string.delete_note),
            content = stringResource(R.string.are_you_sure_you_want_to_delete_this_note),
            onDismiss = { viewModel.onAction(NotesListAction.OnDialogDismiss) },
            onConfirm = { viewModel.onAction(NotesListAction.OnDeleteNoteConfirmed) }
        )
    }
}

@Composable
fun NotesListScreen(
    modifier: Modifier = Modifier,
    notesListState: NotesListState,
    onAction: (NotesListAction) -> Unit
) {
    when (LocalScreenOrientation.current) {
        ScreenOrientation.Portrait -> NotesListPortraitScreen(
            modifier = modifier,
            onAction = onAction,
            notesListState = notesListState
        )

        ScreenOrientation.Landscape -> NotesListLandscapeScreen(
            modifier = modifier,
            onAction = onAction,
            notesListState = notesListState
        )

        ScreenOrientation.Tablet -> {
//            RegistrationTabletContent(
//                modifier = modifier,
//                onAction = onAction,
//                registrationState = registrationState,
//                onValidate = onValidate,
//                navigateToLogin = navigateToLogin
//            )
        }
    }
}