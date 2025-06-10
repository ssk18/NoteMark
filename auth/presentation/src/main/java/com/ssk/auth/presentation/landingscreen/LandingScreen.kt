package com.ssk.auth.presentation.landingscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.auth.presentation.R
import com.ssk.core.presentation.designsystem.components.NoteMarkActionPrimaryButton
import com.ssk.core.presentation.designsystem.components.NoteMarkActionSecondaryButton
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.core.presentation.ui.LocalScreenOrientation
import com.ssk.core.presentation.ui.ScreenOrientation

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
   when(LocalScreenOrientation.current) {
       ScreenOrientation.Portrait -> LandingScreenPortrait(
           modifier = modifier,
           onSignUpClick = onSignUpClick,
           onSignInClick = onSignInClick
       )

       ScreenOrientation.Landscape -> TODO()
       ScreenOrientation.Tablet -> TODO()
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
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.notemark_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 250.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Your Own Collection of Notes",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Capture your thoughts and ideas",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(20.dp))
            NoteMarkActionPrimaryButton(
                onClick = onSignUpClick,
                title = "Get Started"
            )
            Spacer(modifier = Modifier.height(12.dp))
            NoteMarkActionSecondaryButton(
                title = "Log In",
                onClick = onSignInClick
            )
        }
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
        Image(
            painter = painterResource(id = R.drawable.notemark_background),
            contentDescription = "background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 400.dp)
        )
        Column(
            modifier = Modifier
                .width(400.dp)
                .padding(vertical = 32.dp)
                .fillMaxHeight()
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        bottomStart = 20.dp
                    )
                )
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Your Own Collection of Notes",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Capture your thoughts and ideas",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(20.dp))
            NoteMarkActionPrimaryButton(
                onClick = onSignUpClick,
                title = "Get Started"
            )
            Spacer(modifier = Modifier.height(12.dp))
            NoteMarkActionSecondaryButton(
                title = "Log In",
                onClick = onSignInClick
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
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 350.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = "Your Own Collection of Notes",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Capture your thoughts and ideas",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(20.dp))
            NoteMarkActionPrimaryButton(
                onClick = onSignUpClick,
                title = "Get Started"
            )
            Spacer(modifier = Modifier.height(12.dp))
            NoteMarkActionSecondaryButton(
                title = "Log In",
                onClick = onSignInClick
            )
        }
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