package com.ssk.notes.presentation.notelistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.auth.domain.repository.AuthRepository
import com.ssk.core.domain.Result
import com.ssk.core.domain.notes.Note
import com.ssk.core.domain.notes.NotesRepository
import com.ssk.core.presentation.ui.asUiText
import com.ssk.notes.presentation.notelistscreen.handler.NotesListAction
import com.ssk.notes.presentation.notelistscreen.handler.NotesListEvents
import com.ssk.notes.presentation.notelistscreen.handler.NotesListState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.UUID

class NotesListViewModel(
    private val authRepository: AuthRepository,
    private val notesRepository: NotesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(NotesListState())
    val state = combine(
        _state,
        notesRepository.getNotes()
    ) { state, notes ->
        state.copy(notes = notes)
    }
        .onStart {
            getUsername()
        }
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = NotesListState()
        )

    private val _eventChannel = Channel<NotesListEvents>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: NotesListAction) {
        when (action) {
            NotesListAction.OnAddNoteClicked -> createNote()
            is NotesListAction.OnNoteClicked -> {
                _eventChannel.trySend(NotesListEvents.NavigateToNoteDetail(action.note.id))
            }
        }
    }


    private fun getUsername() {
        viewModelScope.launch {
            val fullName = authRepository.getUsername()
            val username = fullName.split(" ").filter { it.isNotEmpty() }

            val initials = when {
                username.size >= 2 -> {
                    "${username[0].first().uppercase()}${username[1].first().uppercase()}"
                }

                username.size == 1 && username[0].length >= 2 -> {
                    username[0].take(2).uppercase()
                }

                username.size == 1 && username[0].length == 1 -> {
                    username[0].uppercase().repeat(2)
                }

                else -> "??"
            }
            _state.value = _state.value.copy(userInitials = initials)
        }
    }

    private fun createNote() {
        viewModelScope.launch {
            val noteId = UUID.randomUUID().toString()
            val currentTime = Instant.now().toString()

            val newNote = Note(
                id = noteId,
                title = "New Note",
                content = "",
                createdAt = currentTime,
                lastEditedAt = currentTime
            )

            val result = notesRepository.createNote(newNote)

            when (result) {
                is Result.Success -> {
                    _eventChannel.send(NotesListEvents.NavigateToNoteDetail(newNote.id))
                }

                is Result.Error -> {
                    _eventChannel.send(NotesListEvents.ShowError(result.error.asUiText()))
                }
            }
        }
    }

//    private fun getNotes() {
//        viewModelScope.launch {
//            val notes = notesRepository.
//            _state.update { it.copy(notes = notes) }
//        }
//    }

}