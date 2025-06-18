package com.ssk.notemark.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Landing : NavKey

@Serializable
data object Register : NavKey

@Serializable
data object Login : NavKey

@Serializable
data object NotesList : NavKey

@Serializable
data class NoteDetail(val noteId: String) : NavKey
