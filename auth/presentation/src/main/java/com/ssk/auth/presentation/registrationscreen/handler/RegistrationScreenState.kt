package com.ssk.auth.presentation.registrationscreen.handler

import com.ssk.core.presentation.designsystem.components.SnackbarType
import com.ssk.core.presentation.ui.UiText

data class RegistrationScreenState(
    val isRegistering: Boolean = false,
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val email: String = "",
    val isPasswordVisible: Boolean = false,
    val usernameError: UiText? = null,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
    val confirmPasswordError: UiText? = null,
    val snackbarType: SnackbarType = SnackbarType.Success
) {
    val canUserRegister: Boolean
        get() = usernameError == null && emailError == null &&
                passwordError == null && confirmPasswordError == null &&
                username.isNotBlank() && email.isNotBlank() &&
                password.isNotBlank() && confirmPassword.isNotBlank()
}
