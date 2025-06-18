package com.ssk.notes.data.network

import com.ssk.core.domain.notes.Note
import kotlinx.serialization.Serializable

@Serializable
data class NoteRequestDto(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val lastEditedAt: String
)

@Serializable
data class NoteResponseDto(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val lastEditedAt: String
)

@Serializable
data class NotesListResponseDto(
    val notes: List<NoteResponseDto>,
    val total: Int
)

fun Note.toNoteRequestDto(): NoteRequestDto {
    return NoteRequestDto(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        lastEditedAt = lastEditedAt
    )
}

fun NoteResponseDto.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        lastEditedAt = lastEditedAt
    )
}

fun NoteRequestDto.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        lastEditedAt = lastEditedAt
    )
}