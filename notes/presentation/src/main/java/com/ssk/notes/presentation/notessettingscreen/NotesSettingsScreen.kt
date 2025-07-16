package com.ssk.notes.presentation.notessettingscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.core.presentation.designsystem.components.NoteMarkScaffold
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.ui.ObserveAsEvents
import com.ssk.notes.presentation.R
import com.ssk.notes.presentation.notessettingscreen.components.NotesSettingsTopBar
import com.ssk.notes.presentation.notessettingscreen.handler.NotesSettingAction
import com.ssk.notes.presentation.notessettingscreen.handler.NotesSettingsEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesSettingsScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: NotesSettingsViewModel = koinViewModel(),
    navigateToLogin: () -> Unit,
    navigateToNotesList: () -> Unit
) {
    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            NotesSettingsEvent.NavigateToLogin -> navigateToLogin()
            NotesSettingsEvent.NavigateToNotesList -> navigateToNotesList()
        }
    }

    NotesSettingsScreen(
        modifier = modifier,
        onAction = viewModel::onAction
    )
}

@Composable
fun NotesSettingsScreen(
    modifier: Modifier = Modifier,
    onAction: (NotesSettingAction) -> Unit,
) {
    NoteMarkScaffold(
        modifier = modifier,
        topBar = {
            NotesSettingsTopBar(
                onBackClick = { onAction(NotesSettingAction.OnBackClick) },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(vertical = it.calculateTopPadding(), horizontal = 16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onAction(NotesSettingAction.OnLogOutClick) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.log_out),
                        contentDescription = "log out",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                Text(
                    text = "Log Out",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview
@Composable
fun NotesSettingsScreenPreview() {
    NoteMarkTheme {
        NotesSettingsScreen(
            onAction = {}
        )
    }
}