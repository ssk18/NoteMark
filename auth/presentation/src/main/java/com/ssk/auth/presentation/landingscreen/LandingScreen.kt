package com.ssk.auth.presentation.landingscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.auth.presentation.R
import com.ssk.core.presentation.designsystem.components.NoteMarkActionPrimaryButton
import com.ssk.core.presentation.designsystem.components.NoteMarkActionSecondaryButton
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val constraints = this
        val isLandscape = constraints.maxWidth > constraints.maxHeight
        val isTablet = constraints.maxWidth >= 600.dp
        
        // Calculate responsive dimensions based on constraints
        val contentWidth = when {
            constraints.maxWidth > 1200.dp -> constraints.maxWidth * 0.3f
            constraints.maxWidth > 800.dp -> constraints.maxWidth * 0.35f  
            else -> constraints.maxWidth * 0.45f
        }
        
        val verticalPadding = constraints.maxHeight * 0.1f
        val imageContentScale = if (isLandscape || isTablet) ContentScale.FillWidth else ContentScale.Crop

        if (isLandscape || isTablet) {
            // Landscape Layout - Image fills background, content overlaid
            Box(modifier = Modifier.fillMaxSize()) {
                // Full background image with better scaling
                Image(
                    painter = painterResource(id = R.drawable.notemark_background),
                    contentDescription = null,
                    contentScale = imageContentScale,
                    modifier = Modifier.fillMaxSize()
                )
                
                // Content section with responsive positioning
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = verticalPadding),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    ContentSection(
                        modifier = Modifier
                            .width(contentWidth)
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(
                                    topStart = 20.dp,
                                    bottomStart = 20.dp
                                )
                            )
                            .padding(
                                horizontal = (constraints.maxWidth * 0.02f).coerceAtLeast(16.dp),
                                vertical = (constraints.maxHeight * 0.03f).coerceAtLeast(16.dp)
                            ),
                        onSignUpClick = onSignUpClick,
                        onSignInClick = onSignInClick
                    )
                }
            }
        } else {
            // Portrait Layout
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.notemark_background),
                    contentDescription = null,
                    contentScale = imageContentScale,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = constraints.maxHeight * 0.35f)
                )
                ContentSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(constraints.maxHeight * 0.4f)
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp
                            )
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    onSignUpClick = onSignUpClick,
                    onSignInClick = onSignInClick
                )
            }
        }
    }
}


@Composable
private fun ContentSection(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Your Own Collection of Notes",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Capture your thoughts and ideas",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(Modifier.height(16.dp))
        NoteMarkActionPrimaryButton(
            onClick = onSignUpClick,
            title = "Get Started"
        )
        Spacer(Modifier.height(8.dp))
        NoteMarkActionSecondaryButton(
            title = "Log In",
            onClick = onSignInClick
        )
    }
}

@Preview
@Composable
fun LandingScreenPreview() {
    NoteMarkTheme {
        LandingScreen(
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}
