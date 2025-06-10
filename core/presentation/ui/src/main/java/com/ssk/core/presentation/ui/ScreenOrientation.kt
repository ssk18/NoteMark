package com.ssk.core.presentation.ui

sealed interface ScreenOrientation {
    data object Portrait : ScreenOrientation
    data object Landscape : ScreenOrientation
    data object Tablet : ScreenOrientation
}