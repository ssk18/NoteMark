package com.ssk.core.presentation.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.ssk.core.presentation.designsystem.R

val PasswordVisibleIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.eye)

val PasswordNotVisibleIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.eye_off)