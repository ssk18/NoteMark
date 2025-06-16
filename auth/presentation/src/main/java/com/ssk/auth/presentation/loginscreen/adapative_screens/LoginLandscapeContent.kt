package com.ssk.auth.presentation.loginscreen.adapative_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssk.auth.presentation.loginscreen.handler.LoginAction
import com.ssk.auth.presentation.loginscreen.handler.LoginState
import com.ssk.core.presentation.designsystem.components.NoteMarkActionPrimaryButton
import com.ssk.core.presentation.designsystem.components.NoteMarkTextField
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.designsystem.theme.NotemarkPrimary

@Composable
fun LoginLandscapeContent(
    modifier: Modifier = Modifier,
    onAction: (LoginAction) -> Unit,
    loginState: LoginState,
    onValidate: (String) -> Unit = {},
    navigateToLogin: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight * 0.1f
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(NotemarkPrimary),
    ) {
        Row(
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
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "Log In",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Capture your thoughts and ideas.",
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteMarkTextField(
                    value = loginState.email,
                    onValueChange = {
                        onAction(LoginAction.OnEmailChange(it))
                    },
                    label = "Email",
                    placeholder = "john.doe@example.com",
                    supportingText = loginState.emailError?.asString() ?: "",
                    isError = loginState.emailError != null,
                    imeAction = ImeAction.Next,
                    onImeAction = {
                        onValidate("email")
                        focusManager.moveFocus(FocusDirection.Down)
                    },
                    onFocusLost = { onValidate("email") }
                )
                Spacer(Modifier.height(16.dp))

                NoteMarkTextField(
                    value = loginState.password,
                    onValueChange = {
                        onAction(LoginAction.OnPasswordChange(it))
                    },
                    label = "Password",
                    placeholder = "Password",
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

                Spacer(Modifier.height(16.dp))

                NoteMarkActionPrimaryButton(
                    title = "Log in",
                    onClick = {
                        onAction(LoginAction.OnLogin )
                    },
                    enabled = loginState.canUserLogin
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable {
                            navigateToLogin()
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun LoginLandscapeContentPreview() {
    NoteMarkTheme {
        LoginLandscapeContent(
            onAction = {},
            loginState = LoginState(),
            navigateToLogin = {}
        )
    }
}
