package com.ssk.notes.presentation.notesDetailscreen.notesadaptivescreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
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
import com.ssk.notes.presentation.notesDetailscreen.components.ExtendedFab
import com.ssk.notes.presentation.notesDetailscreen.components.NoteDetailsViewBar
import com.ssk.notes.presentation.notesDetailscreen.components.ViewMode
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailState
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailsAction

@Composable
fun NoteDetailsViewScreen(
    modifier: Modifier = Modifier,
    state: NoteDetailState,
    onAction: (NoteDetailsAction) -> Unit
) {
    NoteMarkScaffold(
        topBar = {
            NoteDetailsViewBar(
                onBackClicked = {
                    onAction(NoteDetailsAction.OnBackClicked)
                }
            )
        },
        floatingActionButton = {
            ExtendedFab(
                onEditClick = {
                    onAction(NoteDetailsAction.OnEditNoteClicked(state.noteMode))
                },
                onViewClick = {
                    onAction(NoteDetailsAction.OnReadNoteClicked)
                },
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.onPrimary
                )
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical  = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = state.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
            )

            HorizontalDivider(
                modifier = Modifier
                    .expandWidth(16.dp),
                color = MaterialTheme.colorScheme.surface
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    Text(
                        text = "Date created",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = state.createdAt.toString(),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    Text(
                        text = "Last edited",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = state.createdAt.toString(),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .expandWidth(16.dp),
                color = MaterialTheme.colorScheme.surface
            )
            Text(
                text = state.content,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsViewScreenPreview() {
    NoteMarkTheme {
        NoteDetailsViewScreen(
            state = NoteDetailState(
                title = "Title",
                content = "aarfwqt dfafqvcwasw farfwacasvcw faqfafcasf",
                createdAt = "2025-08-01",
                lastEditedAt = "2025-08-01",
                noteMode = ViewMode.VIEW,
                showDialog = false
            ),
            onAction = {}
        )
    }
}