package com.ssk.core.presentation.designsystem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun NoteMarkActionPrimaryButton(
    modifier: Modifier = Modifier,
    title: String,
    isLoading: Boolean = false,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        enabled = enabled
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(25.dp)
                    .padding(top = 4.dp, bottom = 4.dp)
            )
        } else {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun NoteMarkActionSecondaryButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
fun NoteMarkActionButtonPreview() {
    NoteMarkTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NoteMarkActionPrimaryButton(
                title = "Get Started",
                onClick = {},
                enabled = true,
                isLoading = true
            )
            NoteMarkActionSecondaryButton(
                title = "Log In",
                onClick = {}
            )
        }
    }
}