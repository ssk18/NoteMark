package com.ssk.auth.presentation.registrationscreen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.auth.presentation.R
import com.ssk.auth.presentation.registrationscreen.adapative_screens.RegistrationLandscapeContent
import com.ssk.auth.presentation.registrationscreen.adapative_screens.RegistrationPortraitContent
import com.ssk.auth.presentation.registrationscreen.adapative_screens.RegistrationTabletContent
import com.ssk.auth.presentation.registrationscreen.handler.RegisterEvent
import com.ssk.auth.presentation.registrationscreen.handler.RegistrationScreenAction
import com.ssk.auth.presentation.registrationscreen.handler.RegistrationScreenState
import com.ssk.core.presentation.designsystem.components.NoteMarkScaffold
import com.ssk.core.presentation.designsystem.components.NoteMarkSnackBar
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.ui.LocalScreenOrientation
import com.ssk.core.presentation.ui.ObserveAsEvents
import com.ssk.core.presentation.ui.ScreenOrientation
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreenRoot(
    modifier: Modifier,
    viewModel: RegistrationViewModel = koinViewModel(),
    navigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    ObserveAsEvents(viewModel.eventChannel) { event ->
        when (event) {
            is RegisterEvent.Error -> {
                snackBarHostState.showSnackbar(
                    message = event.error.asString(context)
                )
            }

            RegisterEvent.RegistrationSuccess -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(R.string.registration_successful)
                )
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
        RegistrationScreen(
            onAction = viewModel::onAction,
            registrationState = state,
            onValidate = viewModel::validateField,
            navigateToLogin = navigateToLogin
        )
    }

    if (state.isRegistering) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    onAction: (RegistrationScreenAction) -> Unit,
    registrationState: RegistrationScreenState,
    onValidate: (String) -> Unit = {},
    navigateToLogin: () -> Unit
) {
    when (LocalScreenOrientation.current) {
        ScreenOrientation.Portrait -> RegistrationPortraitContent(
            modifier = modifier,
            onAction = onAction,
            registrationState = registrationState,
            onValidate = onValidate,
            navigateToLogin = navigateToLogin
        )

        ScreenOrientation.Landscape -> RegistrationLandscapeContent(
            modifier = modifier,
            onAction = onAction,
            registrationState = registrationState,
            onValidate = onValidate,
            navigateToLogin = navigateToLogin
        )

        ScreenOrientation.Tablet -> {
            RegistrationTabletContent(
                modifier = modifier,
                onAction = onAction,
                registrationState = registrationState,
                onValidate = onValidate,
                navigateToLogin = navigateToLogin
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    NoteMarkTheme {
        RegistrationScreen(
            onAction = {},
            registrationState = RegistrationScreenState(),
            navigateToLogin = {}
        )
    }
}