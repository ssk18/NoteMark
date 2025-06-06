package com.ssk.notemark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.ssk.auth.presentation.landingscreen.LandingScreen
import com.ssk.core.presentation.designsystem.theme.NoteMarkTheme
import com.ssk.notemark.navigation.NoteMarkNavigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var keepSplashOnScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            keepSplashOnScreen
        }

        lifecycleScope.launch {
            delay(3000)
            keepSplashOnScreen = false
        }
        enableEdgeToEdge()
        setContent {
            NoteMarkTheme {
                NoteMarkNavigation(
                    modifier = Modifier
                )
            }
        }
    }
}