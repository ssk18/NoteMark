package com.ssk.auth.presentation.registrationscreen.handler

sealed interface RegistrationScreenAction {
    data class OnUserNameChange(val username: String) : RegistrationScreenAction
    data class OnPasswordChange(val password: String) : RegistrationScreenAction
    data class OnConfirmPasswordChange(val confirmPassword: String) : RegistrationScreenAction
    data class OnEmailChange(val email: String) : RegistrationScreenAction
    data object OnSignUpClick : RegistrationScreenAction
    data object OnSignInClick : RegistrationScreenAction
}