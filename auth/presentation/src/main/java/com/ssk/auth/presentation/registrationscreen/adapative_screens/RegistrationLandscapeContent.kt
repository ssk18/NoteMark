package com.ssk.auth.presentation.registrationscreen.adapative_screens

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
import com.ssk.auth.presentation.registrationscreen.handler.RegistrationScreenAction
import com.ssk.auth.presentation.registrationscreen.handler.RegistrationScreenState
import com.ssk.core.presentation.designsystem.components.NoteMarkActionPrimaryButton
import com.ssk.core.presentation.designsystem.components.NoteMarkTextField
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.designsystem.theme.NotemarkPrimary

@Composable
fun RegistrationLandscapeContent(
    modifier: Modifier = Modifier,
    onAction: (RegistrationScreenAction) -> Unit,
    registrationState: RegistrationScreenState,
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
                .padding(horizontal = 32.dp, vertical = 32.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "Create account",
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
                    value = registrationState.username,
                    onValueChange = {
                        onAction(RegistrationScreenAction.OnUserNameChange(it))
                    },
                    label = "Username",
                    placeholder = "john.doe",
                    supportingText = registrationState.usernameError?.asString() ?: "",
                    isError = registrationState.usernameError != null,
                    imeAction = ImeAction.Next,
                    onImeAction = {
                        onValidate("username")
                        focusManager.moveFocus(FocusDirection.Down)
                    },
                    onFocusLost = { onValidate("username") }
                )

                Spacer(Modifier.height(16.dp))

                NoteMarkTextField(
                    value = registrationState.email,
                    onValueChange = {
                        onAction(RegistrationScreenAction.OnEmailChange(it))
                    },
                    label = "Email",
                    placeholder = "john.doe@example.com",
                    supportingText = registrationState.emailError?.asString() ?: "",
                    isError = registrationState.emailError != null,
                    imeAction = ImeAction.Next,
                    onImeAction = {
                        onValidate("email")
                        focusManager.moveFocus(FocusDirection.Down)
                    },
                    onFocusLost = { onValidate("email") }
                )
                Spacer(Modifier.height(16.dp))

                NoteMarkTextField(
                    value = registrationState.password,
                    onValueChange = {
                        onAction(RegistrationScreenAction.OnPasswordChange(it))
                    },
                    label = "Password",
                    placeholder = "Password",
                    supportingText = registrationState.passwordError?.asString() ?: "",
                    focusedSupportingText = "Use 8+ characters with a number or symbol for better security",
                    isError = registrationState.passwordError != null,
                    isPassword = true,
                    isPasswordVisible = registrationState.isPasswordVisible,
                    imeAction = ImeAction.Next,
                    onImeAction = {
                        onValidate("password")
                        focusManager.moveFocus(FocusDirection.Down)
                    },
                    onFocusLost = { onValidate("password") },
                    onToggleShowPassword = {
                        onAction(RegistrationScreenAction.OnTogglePasswordVisibility)
                    }
                )

                Spacer(Modifier.height(16.dp))

                NoteMarkTextField(
                    value = registrationState.confirmPassword,
                    onValueChange = {
                        onAction(RegistrationScreenAction.OnConfirmPasswordChange(it))
                    },
                    label = "Password",
                    placeholder = "Confirm your Password",
                    supportingText = registrationState.confirmPasswordError?.asString() ?: "",
                    isError = registrationState.confirmPasswordError != null,
                    isPassword = true,
                    isPasswordVisible = registrationState.isPasswordVisible,
                    imeAction = ImeAction.Done,
                    onImeAction = {
                        onValidate("confirmPassword")
                        focusManager.clearFocus()
                    },
                    onFocusLost = { onValidate("confirmPassword") },
                    onToggleShowPassword = {
                        onAction(RegistrationScreenAction.OnTogglePasswordVisibility)
                    }
                )

                Spacer(Modifier.height(24.dp))

                NoteMarkActionPrimaryButton(
                    title = "Create Account",
                    onClick = {
                        onAction(RegistrationScreenAction.OnRegister)
                    },
                    enabled = registrationState.canUserRegister
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Already have an account?",
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
fun RegistrationLandscapeContentPreview() {
    NoteMarkTheme {
        RegistrationLandscapeContent(
            onAction = {},
            registrationState = RegistrationScreenState(),
            navigateToLogin = {}
        )
    }
}
