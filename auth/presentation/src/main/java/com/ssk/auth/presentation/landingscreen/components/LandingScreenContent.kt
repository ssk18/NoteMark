package com.ssk.auth.presentation.landingscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ssk.auth.presentation.R
import com.ssk.core.presentation.designsystem.components.NoteMarkActionPrimaryButton
import com.ssk.core.presentation.designsystem.components.NoteMarkActionSecondaryButton

@Composable
fun LandingScreenContent(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.your_own_collection_of_notes),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.capture_your_thoughts_and_ideas),
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(32.dp))
        NoteMarkActionPrimaryButton(
            onClick = onSignUpClick,
            title = stringResource(R.string.get_started),
            enabled = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkActionSecondaryButton(
            title = stringResource(R.string.log_in),
            onClick = onSignInClick
        )
    }
}