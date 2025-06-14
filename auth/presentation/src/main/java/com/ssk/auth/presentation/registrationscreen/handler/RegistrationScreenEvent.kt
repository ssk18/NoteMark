package com.ssk.auth.presentation.registrationscreen.handler

import com.ssk.core.presentation.ui.UiText

sealed interface RegisterEvent {
    data object RegistrationSuccess: RegisterEvent
    data class Error(val error: UiText): RegisterEvent
}

enum class ValidationField {
    USERNAME,
    EMAIL, 
    PASSWORD,
    CONFIRM_PASSWORD
}