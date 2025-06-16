package com.ssk.auth.presentation.loginscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.auth.presentation.loginscreen.adapative_screens.LoginLandscapeContent
import com.ssk.auth.presentation.loginscreen.adapative_screens.LoginPortraitContent
import com.ssk.auth.presentation.loginscreen.adapative_screens.LoginTabletContent
import com.ssk.auth.presentation.loginscreen.handler.LoginAction
import com.ssk.auth.presentation.loginscreen.handler.LoginEvent
import com.ssk.auth.presentation.loginscreen.handler.LoginState
import com.ssk.core.presentation.designsystem.components.NoteMarkScaffold
import com.ssk.core.presentation.designsystem.components.NoteMarkSnackBar
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.ui.LocalScreenOrientation
import com.ssk.core.presentation.ui.ObserveAsEvents
import com.ssk.core.presentation.ui.ScreenOrientation
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    modifier: Modifier,
    viewModel: LoginViewModel = koinViewModel(),
    navigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            is LoginEvent.Error -> {
                snackBarHostState.showSnackbar(
                    message = event.error.asString(context)
                )
            }

            LoginEvent.LoginSuccess -> {
                onLoginSuccess()
            }
        }
    }

    NoteMarkScaffold(
        modifier = modifier,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                NoteMarkSnackBar(
                    hostState = snackBarHostState,
                    snackBarType = state.snackbarType,
                    modifier = Modifier
                        .fillMaxWidth()
                        .imePadding()
                        .navigationBarsPadding()
                )
            }
        }
    ) {
        LoginScreen(
            onAction = viewModel::onAction,
            loginState = state,
            onValidate = viewModel::validateField,
            navigateToRegister = navigateToRegister
        )
    }

    if (state.isLoggingIn) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onAction: (LoginAction) -> Unit,
    loginState: LoginState,
    onValidate: (String) -> Unit = {},
    navigateToRegister: () -> Unit
) {
    when (LocalScreenOrientation.current) {
        ScreenOrientation.Portrait -> LoginPortraitContent(
            modifier = modifier,
            onAction = onAction,
            loginState = loginState,
            onValidate = onValidate,
            navigateToRegister = navigateToRegister
        )

        ScreenOrientation.Landscape -> LoginLandscapeContent(
            modifier = modifier,
            onAction = onAction,
            loginState = loginState,
            onValidate = onValidate,
            navigateToLogin = navigateToRegister
        )

        ScreenOrientation.Tablet -> LoginTabletContent(
            modifier = modifier,
            onAction = onAction,
            loginState = loginState,
            onValidate = onValidate,
            navigateToLogin = navigateToRegister
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    NoteMarkTheme {
        LoginScreen(
            onAction = {},
            loginState = LoginState(),
            navigateToRegister = {}
        )
    }
}