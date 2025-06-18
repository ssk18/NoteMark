package com.ssk.notes.presentation.notelistscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ssk.core.presentation.designsystem.theme.NotemarkOnPrimary
import com.ssk.notes.presentation.R
import com.ssk.notes.presentation.notelistscreen.handler.NotesListState

@Composable
fun NotesListTopBar(
    modifier: Modifier = Modifier,
    notesListState: NotesListState
) {
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
}