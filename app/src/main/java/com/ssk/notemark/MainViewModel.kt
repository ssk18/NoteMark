package com.ssk.notemark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.auth.domain.repository.AuthRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state
        .onStart {
            checkAuthState()
        }
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = MainState()
        )

    private fun checkAuthState() {
        viewModelScope.launch {
            try {
                val isLoggedIn = authRepository.isLoggedIn()
                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoggedIn = isLoggedIn
                    )
                }
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                }
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}