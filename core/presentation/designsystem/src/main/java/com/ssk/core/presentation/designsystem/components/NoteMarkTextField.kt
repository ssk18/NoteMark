package com.ssk.core.presentation.designsystem.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.designsystem.theme.PasswordNotVisibleIcon
import com.ssk.core.presentation.designsystem.theme.PasswordVisibleIcon

@Composable
fun NoteMarkTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    supportingText: String = "",
    focusedSupportingText: String = "",
    isFocused: Boolean = false,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    placeholder: String,
    imeAction: ImeAction = ImeAction.Default,
    onImeAction: () -> Unit = {},
    onFocusLost: () -> Unit = {},
    onToggleShowPassword: (() -> Unit)? = null,
) {
    val focusRequester = remember { FocusRequester() }
    var isFieldFocused by remember { mutableStateOf(false) }

    LaunchedEffect(isFocused) {
        if (isFocused) {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    Log.d("NoteMarkTextField", "Focus changed: ${focusState.isFocused}")
                    isFieldFocused = focusState.isFocused
                    if (!focusState.isFocused) {
                        onFocusLost()
                    }
                }
                .focusRequester(focusRequester),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            supportingText = {
                Log.d("NoteMarkTextField", "supportingText: $isFieldFocused ${focusedSupportingText.isNotEmpty()}")
                val textToShow = when {
                    isError -> supportingText
                    isFieldFocused && focusedSupportingText.isNotEmpty() -> focusedSupportingText
                    else -> supportingText
                }
                Text(
                    text = textToShow,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            isError = isError,
            visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (isPassword) {
                    Icon(
                        imageVector = if (isPasswordVisible) PasswordVisibleIcon else PasswordNotVisibleIcon,
                        contentDescription = "Password visibility",
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .clickable() {
                                onToggleShowPassword?.invoke()
                            }
                    )
                }
            },
            colors = appTextFieldColors(),
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            keyboardOptions = KeyboardOptions(imeAction = imeAction),
            keyboardActions = KeyboardActions(onAny = { onImeAction() })
        )
    }
}

@Composable
fun appTextFieldColors(): TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
        errorPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        errorContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        unfocusedBorderColor = Color.Transparent,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        errorBorderColor = MaterialTheme.colorScheme.error,
        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorSupportingTextColor = MaterialTheme.colorScheme.error
    )
}

@Preview(showBackground = true)
@Composable
fun NoteMarkTextFieldPreview() {
    NoteMarkTheme {
        NoteMarkTextField(
            value = "",
            onValueChange = {},
            label = "Email",
            placeholder = "Enter your email",
            isFocused = false,
            isPassword = false,
            supportingText = "",
            isError = false,
            onToggleShowPassword = {}
        )
    }
}