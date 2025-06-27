package com.ssk.notes.presentation.notelistscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.core.domain.formatDate
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    createdAt: String,
    title: String,
    content: String,
    maxCharacters: Int
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceContainerLowest
            )
            .shadow(
                elevation = 8.dp,
                spotColor = MaterialTheme.colorScheme.onSurface,
                ambientColor = MaterialTheme.colorScheme.onSurface
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = formatDate(createdAt),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Text(
                text = content.take(maxCharacters),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    NoteMarkTheme {
        NoteCard(
            createdAt = "2025-06-18T13:55:01.503656Z",
            title = "Title",
            content = "Lets build beautiful apps with compose fsfaf afafswgwsgb afastfgwrgvgwrfdgq fsafafaf afwafafvqaeFVQWEFVCWADVFCWWSFDVS  ASFWSAV SDFWSFGBVWSFB V",
            maxCharacters = 150
        )
    }
}