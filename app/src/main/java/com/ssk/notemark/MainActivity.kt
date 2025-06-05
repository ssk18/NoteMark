 package com.ssk.notemark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.ssk.notemark.ui.theme.NotemarkTheme
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
            NotemarkTheme {

            }
        }
    }
}