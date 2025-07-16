package com.ssk.notes.presentation.notessettingscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.notes.presentation.notessettingscreen.handler.NotesSettingAction
import com.ssk.notes.presentation.notessettingscreen.handler.NotesSettingsEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NotesSettingsViewModel : ViewModel() {

    private val _eventChannel = Channel<NotesSettingsEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: NotesSettingAction) {
        viewModelScope.launch {
            when (action) {
                NotesSettingAction.OnBackClick -> {
                    _eventChannel.send(NotesSettingsEvent.NavigateToNotesList)
                }

                NotesSettingAction.OnLogOutClick -> {
                    _eventChannel.send(NotesSettingsEvent.NavigateToLogin)
                }
            }
        }
    }

}