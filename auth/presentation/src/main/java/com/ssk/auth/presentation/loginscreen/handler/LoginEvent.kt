package com.ssk.auth.presentation.loginscreen.handler

import com.ssk.core.presentation.ui.UiText

sealed interface LoginEvent {
    data object LoginSuccess: LoginEvent
    data class Error(val error: UiText): LoginEvent
}