package com.ssk.auth.presentation.registrationscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.auth.presentation.registrationscreen.adapative_screens.RegistrationLandscapeContent
import com.ssk.auth.presentation.registrationscreen.adapative_screens.RegistrationPortraitContent
import com.ssk.auth.presentation.registrationscreen.adapative_screens.RegistrationTabletContent
import com.ssk.auth.presentation.registrationscreen.handler.RegistrationScreenAction
import com.ssk.auth.presentation.registrationscreen.handler.RegistrationScreenState
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.ui.LocalScreenOrientation
import com.ssk.core.presentation.ui.ScreenOrientation
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreenRoot(
    modifier: Modifier,
    viewModel: RegistrationViewModel = koinViewModel(),
    navigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RegistrationScreen(
        modifier = modifier,
        onAction = viewModel::onAction,
        registrationState = state,
        onValidate = viewModel::validateField,
        navigateToLogin = navigateToLogin
    )
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