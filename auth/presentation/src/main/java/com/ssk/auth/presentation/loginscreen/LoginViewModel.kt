package com.ssk.auth.presentation.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.auth.domain.repository.AuthRepository
import com.ssk.auth.presentation.R
import com.ssk.auth.presentation.loginscreen.handler.LoginAction
import com.ssk.auth.presentation.loginscreen.handler.LoginEvent
import com.ssk.auth.presentation.loginscreen.handler.LoginState
import com.ssk.core.domain.Result
import com.ssk.core.presentation.designsystem.components.SnackbarType
import com.ssk.core.presentation.ui.UiText
import com.ssk.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _eventChannel = Channel<LoginEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnEmailChange -> {
                _state.update {
                    it.copy(email = action.email, emailError = null)
                }
            }
            LoginAction.OnLogin -> login()
            is LoginAction.OnPasswordChange -> {
                _state.update {
                    it.copy(password = action.password, passwordError = null)
                }
            }
            LoginAction.OnTogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoggingIn = true) }
            val loginResponse = authRepository.login(
                email = state.value.email,
                password = state.value.password
            )
            _state.update { it.copy(isLoggingIn = false) }

            when (loginResponse) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            snackbarType = SnackbarType.Error
                        )
                    }
                    _eventChannel.send(LoginEvent.Error(loginResponse.error.asUiText()))
                }
                is Result.Success -> {
                    _eventChannel.send(LoginEvent.LoginSuccess)
                }
            }
        }
    }

    fun validateField(field: String) {
        when (field) {
            "email" -> validateEmail(state.value.email)
            "password" -> validatePassword(state.value.password)
        }
    }

    private fun validateEmail(email: String) {
        val error = when {
            email.isBlank() -> null
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                UiText.DynamicString("“Invalid email provided")

            else -> null
        }
        _state.update { it.copy(emailError = error) }
    }

    private fun validatePassword(password: String) {
        val error = when {
            password.isBlank() -> null
            password.length < 8 -> UiText.StringResource(R.string.password_validation_multiline)

            !password.any { it.isDigit() || !it.isLetterOrDigit() } ->
                UiText.StringResource(R.string.password_validation_symbol)

            else -> null
        }
        _state.update { it.copy(passwordError = error) }
    }

}