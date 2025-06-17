package com.ssk.notes.presentation.notelistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.core.presentation.designsystem.components.NoteMarkFab
import com.ssk.core.presentation.designsystem.components.NoteMarkScaffold
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.designsystem.theme.NotemarkOnPrimary
import com.ssk.notes.presentation.R
import com.ssk.notes.presentation.notelistscreen.components.UserProfileCard

@Composable
fun NotesListScreen(
    modifier: Modifier = Modifier
) {
    NoteMarkScaffold(
        modifier = modifier,
        topBar = {
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
                    userInitials = "SA"
                )
            }
        },
        floatingActionButton = {
            NoteMarkFab(
                onClick = {}
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
        NotesListScreen()
    }
}