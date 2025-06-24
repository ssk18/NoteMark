package com.ssk.notemark

sealed interface MainAction {
    data object CheckAuthState : MainAction
}