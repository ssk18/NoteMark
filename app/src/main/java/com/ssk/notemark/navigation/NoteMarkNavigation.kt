package com.ssk.notemark.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.ssk.notemark.MainViewModel
import com.ssk.notes.presentation.notelistscreen.NotesListScreenRoot
import com.ssk.notes.presentation.notesDetailscreen.NoteDetailsScreenRoot
import com.ssk.notes.presentation.notessettingscreen.NotesSettingsScreenRoot
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

@Composable
fun NoteMarkNavigation(
    modifier: Modifier = Modifier
) {
    val mainViewModel: MainViewModel = koinViewModel()
    val mainState by mainViewModel.state.collectAsStateWithLifecycle()

    if (mainState.isLoading) {
        return // Splash screen will be shown by MainActivity
    }

    val initialScreen = if (mainState.isLoggedIn) NotesList else Landing
    val backStack = rememberNavBackStack(initialScreen)

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
                                backStack.removeLastOrNull()
                                backStack.add(Login)
                            },
                            onSignUpClick = {
                                backStack.removeLastOrNull()
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
                                backStack.removeLastOrNull()
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
                                backStack.removeLastOrNull()
                                backStack.add(Register)
                            },
                            onLoginSuccess = {
                                backStack.clear()
                                backStack.add(NotesList)
                            }
                        )
                    }
                }

                NotesList -> {
                    NavEntry(
                        key = key
                    ) {
                        NotesListScreenRoot(
                            modifier = modifier,
                            navigateToNoteDetail = {
                                backStack.add(NoteDetail(it))
                            },
                            navigateToSettings = {
                                backStack.add(Settings)
                            }
                        )
                    }
                }

                is NoteDetail -> {
                    NavEntry(
                        key = key
                    ) {
                        Timber.d("NoteDetail: ${key.noteId}")
                        NoteDetailsScreenRoot(
                            modifier = modifier,
                            viewModel = koinViewModel {
                                parametersOf(key.noteId)
                            },
                            navigateToNotesList = {
                                backStack.removeLastOrNull()
                                backStack.add(NotesList)
                            }
                        )
                    }
                }

                is Settings -> {
                    NavEntry(
                        key = key
                    ) {
                        NotesSettingsScreenRoot(
                            modifier = modifier,
                            navigateToLogin = {
                                backStack.clear()
                                backStack.add(Login)
                            },
                            navigateToNotesList = {
                                backStack.removeLastOrNull()
                                backStack.add(NotesList)
                            }
                        )
                    }
                }

                else -> {}
            } as NavEntry<NavKey>
        }
    )
}