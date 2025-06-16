package com.ssk.auth.presentation.landingscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.auth.presentation.R
import com.ssk.auth.presentation.landingscreen.components.LandingScreenContent
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.ui.LocalScreenOrientation
import com.ssk.core.presentation.ui.ScreenOrientation

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    when (LocalScreenOrientation.current) {
        ScreenOrientation.Portrait -> LandingScreenPortrait(
            modifier = modifier,
            onSignUpClick = onSignUpClick,
            onSignInClick = onSignInClick
        )

        ScreenOrientation.Landscape -> LandingScreenLandscape(
            modifier = modifier,
            onSignUpClick = onSignUpClick,
            onSignInClick = onSignInClick

        )

        ScreenOrientation.Tablet -> LandingScreenTablet(
            modifier = modifier,
            onSignUpClick = onSignUpClick,
            onSignInClick = onSignInClick
        )
    }
}

@Composable
fun LandingScreenPortrait(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.notemark_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 250.dp)
        )

        LandingScreenContent(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .padding(horizontal = 16.dp, vertical = 32.dp),
            onSignUpClick = onSignUpClick,
            onSignInClick = onSignInClick
        )
    }
}

@Composable
fun LandingScreenLandscape(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE0EAFF)),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.notemark_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.CenterStart,
                modifier = Modifier
                    .weight(1f)
            )

            LandingScreenContent(
                modifier = Modifier
                    .weight(1f)
                    .padding(WindowInsets.safeDrawing.asPaddingValues())
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            bottomStart = 20.dp
                        )
                    )
                    .padding(start = 60.dp, end = 40.dp, top = 32.dp, bottom = 32.dp),
                onSignUpClick = onSignUpClick,
                onSignInClick = onSignInClick
            )
        }
    }
}

@Composable
fun LandingScreenTablet(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE0EAFF)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.notemark_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
        )

        LandingScreenContent(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 32.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .padding(32.dp),
            onSignUpClick = onSignUpClick,
            onSignInClick = onSignInClick
        )
    }
}

@Preview
@Composable
fun LandingScreenPreview() {
    NoteMarkTheme {
        LandingScreenPortrait(
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun LandingScreenPreviewLandscape() {
    NoteMarkTheme(darkTheme = true) {
        LandingScreenLandscape(
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}

@Preview(device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun LandingScreenPreviewTablet() {
    NoteMarkTheme(darkTheme = true) {
        LandingScreenTablet(
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}