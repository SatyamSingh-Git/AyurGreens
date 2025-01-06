package com.theclonebox.ayurgreens

import SearchAndNavigationScreen
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theclonebox.ayurgreens.ScreenElements.BottomNavigationBar
import com.theclonebox.ayurgreens.models.ChatViewModel
import com.theclonebox.ayurgreens.screens.BookmarkScreen
import com.theclonebox.ayurgreens.screens.ChatBot
import com.theclonebox.ayurgreens.screens.MainScreen
import ExploreScreen
import com.theclonebox.ayurgreens.ui.theme.AyurGreensTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        val chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        setContent {
            AyurGreensTheme {
                val navController = rememberNavController()  //***
                NavHost(navController = navController, startDestination = "authScreen") {  //***
                    composable("authScreen") { AuthScreen(navController = navController) }   //***
                    composable("mainScreen") { MainScreenWithStatusBar(navController = navController) }  //***
                }
            }
        }
    }
}

@Composable
fun MainScreenWithStatusBar(navController: NavHostController) {  //***
    val context = LocalContext.current
    val greenColor = ContextCompat.getColor(context, android.R.color.holo_green_dark)
    val selectedItem = rememberSaveable { mutableIntStateOf(0) } // Move state here
    val navController = rememberNavController() // Create NavHostController

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(selectedItem = selectedItem) } // Pass state to BottomNavigationBar
    ) { paddingValues ->
        // Custom Status Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp) // Set your desired height here
                .background(Color(greenColor))
        )
        // Main Content

        MainSelectedScreen(
            modifier = Modifier.padding(paddingValues),
            selectedItem = selectedItem,
            navController = navController // Pass navController
        )
    }
}

@Composable
fun MainSelectedScreen(
    modifier: Modifier,
    selectedItem: MutableState<Int>,
    navController: NavHostController
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Display the selected screen
        when (selectedItem.value) {
            0 -> MainScreen(modifier = Modifier, navController = navController) // Pass navController
            1 -> ExploreScreen(modifier = Modifier, navController = navController) // Pass navController
            2 -> SearchAndNavigationScreen(navController = navController) // Pass navController
            3 -> BookmarkScreen(modifier = Modifier)
            4 -> ChatBot(modifier = Modifier.padding(), viewModel = ChatViewModel())
        }
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    AyurGreensTheme {
        MainScreenWithStatusBar(navController = rememberNavController())
    }
}