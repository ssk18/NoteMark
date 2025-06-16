package com.ssk.auth.presentation.registrationscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.auth.presentation.R
import com.ssk.auth.domain.repository.AuthRepository
import com.ssk.auth.presentation.registrationscreen.handler.RegisterEvent
import com.ssk.auth.presentation.registrationscreen.handler.RegistrationScreenAction
import com.ssk.auth.presentation.registrationscreen.handler.RegistrationScreenState
import com.ssk.core.domain.DataError
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

class RegistrationViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegistrationScreenState())
    val state = _state.asStateFlow()

    private val _eventChannel = Channel<RegisterEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onAction(action: RegistrationScreenAction) {
        when (action) {
            is RegistrationScreenAction.OnConfirmPasswordChange -> {
                _state.update {
                    it.copy(confirmPassword = action.confirmPassword, confirmPasswordError = null)
                }
            }

            is RegistrationScreenAction.OnEmailChange -> {
                _state.update {
                    it.copy(email = action.email, emailError = null)
                }
            }

            is RegistrationScreenAction.OnPasswordChange -> {
                _state.update {
                    it.copy(password = action.password, passwordError = null)
                }
            }

            is RegistrationScreenAction.OnUserNameChange -> {
                _state.update {
                    it.copy(username = action.username, usernameError = null)
                }
            }

            RegistrationScreenAction.OnRegister -> register()
            RegistrationScreenAction.OnToggleConfirmPasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }

            RegistrationScreenAction.OnTogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
        }
    }

    fun validateField(field: String) {
        when (field) {
            "username" -> validateUsername(state.value.username)
            "email" -> validateEmail(state.value.email)
            "password" -> validatePassword(state.value.password)
            "confirmPassword" -> validateConfirmPassword(state.value.confirmPassword)
        }
    }

    private fun validateUsername(username: String) {
        val error = when {
            username.isBlank() -> null // Don't show error for empty untouched fields
            username.length < 3 -> UiText.StringResource(R.string.username_too_short)
            username.length > 20 -> UiText.StringResource(R.string.username_too_long)
            else -> null
        }
        _state.update { it.copy(usernameError = error) }
    }

    private fun validateEmail(email: String) {
        val error = when {
            email.isBlank() -> null // Don't show error for empty untouched fields
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                UiText.StringResource(R.string.invalid_email)
            else -> null
        }
        _state.update { it.copy(emailError = error) }
    }

    private fun validatePassword(password: String) {
        val error = when {
            password.isBlank() -> null // Don't show error for empty untouched fields
            password.length < 8 -> UiText.StringResource(R.string.password_validation_multiline)
            !password.any { it.isDigit() || !it.isLetterOrDigit() } ->
                UiText.StringResource(R.string.password_validation_symbol)
            else -> null
        }
        _state.update { it.copy(passwordError = error) }
    }

    private fun validateConfirmPassword(confirmPassword: String) {
        val error = when {
            confirmPassword.isBlank() -> null // Don't show error for empty untouched fields
            confirmPassword != state.value.password -> UiText.StringResource(R.string.passwords_not_match)
            else -> null
        }
        _state.update { it.copy(confirmPasswordError = error) }
    }

    private fun register() {
        viewModelScope.launch {
            _state.update { it.copy(isRegistering = true) }
            val registrationResponse = authRepository.register(
                email = _state.value.email,
                password = _state.value.password,
                username = _state.value.username
            )
            _state.update { it.copy(isRegistering = false) }

            when (registrationResponse) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            snackbarType = SnackbarType.Error
                        )
                    }
                    if (registrationResponse.error == DataError.Network.CONFLICT) {
                        _eventChannel.send(
                            RegisterEvent.Error(
                                UiText.StringResource(
                                    R.string.a_user_with_that_email_already_exists
                                )
                            )
                        )
                    } else {
                        _eventChannel.send(RegisterEvent.Error(registrationResponse.error.asUiText()))
                    }
                }

                is Result.Success -> {
                    _eventChannel.send(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }

}