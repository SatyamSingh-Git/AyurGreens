package com.theclonebox.ayurgreens

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.theclonebox.ayurgreens.ScreenElements.BottomNavigationBar
import com.theclonebox.ayurgreens.screens.MainScreen
import com.theclonebox.ayurgreens.ui.theme.AyurGreensTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AyurGreensTheme {
                MainScreenWithStatusBar()
            }
        }
    }
}

@Composable
fun MainScreenWithStatusBar() {
    val context = LocalContext.current
    val greenColor = ContextCompat.getColor(context, android.R.color.holo_green_dark)

    Scaffold(
        modifier = Modifier.fillMaxSize(), bottomBar = {BottomNavigationBar(rememberSaveable { mutableStateOf(0) })}
    ) { paddingValues ->
        Column {
            // Custom Status Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp) // Set your desired height here
                    .background(Color(greenColor))
            )
            // Main Content
            MainScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    AyurGreensTheme {
        MainScreenWithStatusBar()
    }
}