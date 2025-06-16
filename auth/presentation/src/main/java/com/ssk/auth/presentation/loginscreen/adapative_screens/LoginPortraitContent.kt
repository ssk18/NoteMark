package com.ssk.auth.presentation.loginscreen.adapative_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.auth.presentation.R
import com.ssk.auth.presentation.loginscreen.handler.LoginAction
import com.ssk.auth.presentation.loginscreen.handler.LoginState
import com.ssk.core.presentation.designsystem.components.NoteMarkActionPrimaryButton
import com.ssk.core.presentation.designsystem.components.NoteMarkTextField
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.designsystem.theme.NotemarkPrimary

@Composable
fun LoginPortraitContent(
    modifier: Modifier = Modifier,
    onAction: (LoginAction) -> Unit,
    loginState: LoginState,
    onValidate: (String) -> Unit = {},
    navigateToRegister: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight * 0.1f
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(NotemarkPrimary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sheetHeight)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .fillMaxHeight()
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            Text(
                text = stringResource(R.string.log_in),
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.capture_your_thoughts_and_ideas_subtitle),
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(24.dp))

            NoteMarkTextField(
                value = loginState.email,
                onValueChange = {
                    onAction(LoginAction.OnEmailChange(it))
                },
                label = stringResource(R.string.email),
                placeholder = stringResource(R.string.email_placeholder_john),
                supportingText = loginState.emailError?.asString() ?: "",
                isError = loginState.emailError != null,
                imeAction = ImeAction.Next,
                onImeAction = {
                    onValidate("email")
                    focusManager.moveFocus(FocusDirection.Down)
                },
                onFocusLost = { onValidate("email") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            NoteMarkTextField(
                value = loginState.password,
                onValueChange = {
                    onAction(LoginAction.OnPasswordChange(it))
                },
                label = stringResource(R.string.password),
                placeholder = stringResource(R.string.password_placeholder),
                supportingText = loginState.passwordError?.asString() ?: "",
                isError = loginState.passwordError != null,
                isPassword = true,
                isPasswordVisible = loginState.isPasswordVisible,
                imeAction = ImeAction.Next,
                onImeAction = {
                    onValidate("password")
                    focusManager.moveFocus(FocusDirection.Down)
                },
                onFocusLost = { onValidate("password") },
                onToggleShowPassword = {
                    onAction(LoginAction.OnTogglePasswordVisibility)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            NoteMarkActionPrimaryButton(
                title = stringResource(R.string.login_button),
                onClick = {
                    onAction(LoginAction.OnLogin)
                },
                enabled = loginState.canUserLogin,
                isLoading = loginState.isLoggingIn
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.dont_have_account),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clickable {
                        navigateToRegister()
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPortraitContentPreview() {
    NoteMarkTheme {
        LoginPortraitContent(
            onAction = {},
            loginState = LoginState(),
            navigateToRegister = {}
        )
    }
}
