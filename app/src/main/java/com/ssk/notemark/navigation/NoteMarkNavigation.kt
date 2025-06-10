package com.ssk.notemark.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.ssk.auth.presentation.landingscreen.LandingScreen
import com.ssk.auth.presentation.registrationscreen.RegistrationScreenRoot

@Composable
fun NoteMarkNavigation(
    modifier: Modifier = Modifier
) {
    val backStack = remember { mutableStateListOf<Screen>(Screen.Landing) }

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = { key ->
            when (key) {
                Screen.Landing -> NavEntry(key) { screen ->
                    LandingScreen(
                        onSignInClick = {
                            backStack.add(Screen.Login)
                        },
                        onSignUpClick = {
                            backStack.add(Screen.Register)
                        },
                        modifier = modifier
                    )
                }

                Screen.Register -> NavEntry(key) { screen ->
                    RegistrationScreenRoot(
                        modifier = modifier
                    )
                }

                Screen.Login -> NavEntry(key) { screen ->

                }
            }
        }
    )
}