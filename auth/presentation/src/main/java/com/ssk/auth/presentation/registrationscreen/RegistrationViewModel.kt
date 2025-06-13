package com.ssk.auth.presentation.registrationscreen

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.auth.presentation.registrationscreen.handler.RegistrationScreenAction
import com.ssk.auth.presentation.registrationscreen.handler.RegistrationScreenState
import com.ssk.core.presentation.ui.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {

    private val _state = MutableStateFlow(RegistrationScreenState())
    val state = _state.asStateFlow()

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

            RegistrationScreenAction.OnRegister -> TODO()
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
            username.length < 3 -> UiText.DynamicString("Username must be at least 3 characters")
            else -> null
        }
        _state.update { it.copy(usernameError = error) }
    }

    private fun validateEmail(email: String) {
        val error = when {
            email.isBlank() -> null // Don't show error for empty untouched fields
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                UiText.DynamicString("Invalid email format")

            else -> null
        }
        _state.update { it.copy(emailError = error) }
    }

    private fun validatePassword(password: String) {
        val error = when {
            password.isBlank() -> null // Don't show error for empty untouched fields
            password.length < 8 -> UiText.DynamicString("Password must be at least 8 chars long")
            !password.any { it.isDigit() || !it.isLetterOrDigit() } ->
                UiText.DynamicString("Password must contain number or symbol")

            else -> null
        }
        _state.update { it.copy(passwordError = error) }
    }

    private fun validateConfirmPassword(confirmPassword: String) {
        val error = when {
            confirmPassword.isBlank() -> null // Don't show error for empty untouched fields
            confirmPassword != state.value.password -> UiText.DynamicString("Passwords do not match")
            else -> null
        }
        _state.update { it.copy(confirmPasswordError = error) }
    }

}