package com.ssk.notes.presentation.notesDetailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.notes.presentation.R

@Composable
fun ExtendedFab(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onViewClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .sizeIn(minWidth = 100.dp, minHeight = 52.dp)
    ) {
        Row(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onEditClick()
                },
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = "Edit Note",
                )
            }
            IconButton(
                onClick = { onViewClick() },
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_view),
                    contentDescription = "View Note",
                )
            }
        }
    }
}

@Stable
enum class ViewMode {
    EDIT,
    VIEW
}

@Preview(showBackground = true)
@Composable
fun ExtendedFabPreview() {
    NoteMarkTheme {
        ExtendedFab(
            onEditClick = {},
            onViewClick = {}
        )
    }
}
