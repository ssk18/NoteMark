package com.ssk.notes.presentation.notesDetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.core.domain.Result
import com.ssk.core.domain.notes.Note
import com.ssk.core.domain.notes.NotesRepository
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailState
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailsAction
import com.ssk.notes.presentation.notesDetailscreen.handler.NoteDetailsEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant

class NoteDetailsViewModel(
    private val noteId: String,
    private val notesRepository: NotesRepository
) : ViewModel() {

    val _state = MutableStateFlow(NoteDetailState())
    val state = _state
        .onStart { getNote() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NoteDetailState()
        )

    private val _eventChannel = Channel<NoteDetailsEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: NoteDetailsAction) {
        when (action) {
            NoteDetailsAction.OnCloseNoteClicked -> {
                _state.update { it.copy(showDialog = true) }
            }
            is NoteDetailsAction.OnContentChanged -> {
                _state.update {
                    it.copy(content = action.content)
                }
            }
            NoteDetailsAction.OnNoteSaved -> updateNote()
            is NoteDetailsAction.OnTitleChanged -> {
                _state.update {
                    it.copy(title = action.title)
                }
            }

            NoteDetailsAction.OnDismissDialog -> {
                _state.update { it.copy(showDialog = false) }
            }

            is NoteDetailsAction.OnEditNoteClicked -> TODO()
            NoteDetailsAction.OnReadNoteClicked -> TODO()
            NoteDetailsAction.OnBackClicked -> TODO()
        }
    }

    private fun getNote() {
        viewModelScope.launch {
            notesRepository.getNoteById(noteId)
                .collect { note ->
                    _state.value = _state.value.copy(
                        title = note.title,
                        content = note.content,
                        createdAt = note.createdAt,
                        lastEditedAt = note.lastEditedAt
                    )
                }
        }
    }

    private fun updateNote() {
        viewModelScope.launch {
            val currentState = _state.value
            val updatedNote = Note(
                id = noteId,
                title = currentState.title,
                content = currentState.content,
                createdAt = currentState.createdAt ?: Instant.now().toString(),
                lastEditedAt = Instant.now().toString()
            )
            
            val result = notesRepository.updateNote(updatedNote)
            when (result) {
                is Result.Success -> {
                    _eventChannel.trySend(NoteDetailsEvent.NavigateToNotesList)
                }
                is Result.Error -> {
                    // Handle error - could add ShowError event
                }
            }
        }
    }

}