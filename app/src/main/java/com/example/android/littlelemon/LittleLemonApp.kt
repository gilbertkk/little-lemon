package com.example.android.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android.littlelemon.ui.navigation.AppNavHost

/**
 * Top level composable that represents screens for the application.
 */

@Composable
fun LittleLemonApp(navController: NavHostController = rememberNavController()) {
    AppNavHost(navController = navController)
}