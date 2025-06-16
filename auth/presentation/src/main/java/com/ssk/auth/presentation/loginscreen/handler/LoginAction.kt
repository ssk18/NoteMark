package com.ssk.auth.presentation.loginscreen.handler

sealed interface LoginAction {
    data class OnPasswordChange(val password: String) : LoginAction
    data class OnEmailChange(val email: String) : LoginAction
    data object OnTogglePasswordVisibility : LoginAction
    data object OnLogin : LoginAction
}