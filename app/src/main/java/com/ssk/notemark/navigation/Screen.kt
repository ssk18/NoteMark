package com.ssk.notemark.navigation

sealed class Screen {
    data object Landing : Screen()
    data object Register: Screen()
    data object Login: Screen()
}