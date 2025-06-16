package com.ssk.auth.presentation.loginscreen.handler

import com.ssk.core.presentation.designsystem.components.SnackbarType
import com.ssk.core.presentation.ui.UiText

data class LoginState(
    val isLoggingIn: Boolean = false,
    val password: String = "",
    val email: String = "",
    val isPasswordVisible: Boolean = false,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
    val snackbarType: SnackbarType = SnackbarType.Success
) {
    val canUserLogin: Boolean
        get() = passwordError == null && emailError == null &&
                email.isNotBlank() && password.isNotBlank()
}
