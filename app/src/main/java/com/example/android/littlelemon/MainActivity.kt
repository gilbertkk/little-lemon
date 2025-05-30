package com.example.android.littlelemon

import android.app.Activity.MODE_PRIVATE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                StackNavigation()
            }
        }
    }
}

@Composable
fun StackNavigation() {
    val navController = rememberNavController()

    val sharedPreferences = LocalContext.current.getSharedPreferences("little_lemon", MODE_PRIVATE)
    val isOnboarded = sharedPreferences.getBoolean("isOnboarded", false)
    val startDestination = if (isOnboarded) HomeDestination.route else OnboardingDestination.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(HomeDestination.route) {
            HomeScreen(navController = navController)
        }

        composable(ProfileDestination.route) {
            ProfileScreen(navController = navController, sharedPreferences = sharedPreferences)
        }

        composable(OnboardingDestination.route) {
            OnBoardingScreen(navController = navController)
        }
    }
}