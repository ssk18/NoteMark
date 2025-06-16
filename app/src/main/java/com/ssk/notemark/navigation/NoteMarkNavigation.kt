package com.ssk.notemark.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.ssk.auth.presentation.landingscreen.LandingScreen
import com.ssk.auth.presentation.loginscreen.LoginScreenRoot
import com.ssk.auth.presentation.registrationscreen.RegistrationScreenRoot

@Composable
fun NoteMarkNavigation(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(Landing)

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is Landing -> {
                    NavEntry(
                        key = key,
                    ) {
                        LandingScreen(
                            onSignInClick = {
                                backStack.add(Login)
                            },
                            onSignUpClick = {
                                backStack.add(Register)
                            },
                            modifier = modifier
                        )
                    }
                }

                is Register -> {
                    NavEntry(
                        key = key
                    ) {
                        RegistrationScreenRoot(
                            modifier = modifier,
                            navigateToLogin = {
                                backStack.add(Login)
                            }
                        )
                    }
                }

                Login -> {
                    NavEntry(
                        key = key
                    ) {
                        LoginScreenRoot(
                            modifier = modifier,
                            navigateToRegister = {
                                backStack.add(Register)
                            }
                        )
                    }
                }

                else -> {}
            } as NavEntry<NavKey>
        }
    )
}