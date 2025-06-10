package com.ssk.auth.presentation.registrationscreen.handler

import com.ssk.core.presentation.ui.UiText

sealed interface RegistrationScreenEvent {
    data class ValidationError(
        val field: ValidationField,
        val error: UiText
    ) : RegistrationScreenEvent
    
    data object NavigateToLogin : RegistrationScreenEvent
    data object RegistrationSuccess : RegistrationScreenEvent
}

enum class ValidationField {
    USERNAME,
    EMAIL, 
    PASSWORD,
    CONFIRM_PASSWORD
}