package com.example.android.littlelemon.ui.navigation

import android.app.Activity.MODE_PRIVATE
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.android.littlelemon.SHARED_PREFERENCES_USER_ID
import com.example.android.littlelemon.ui.home.HomeScreen
import com.example.android.littlelemon.ui.onboarding.OnBoardingScreen
import com.example.android.littlelemon.ui.user.UserScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("little_lemon", MODE_PRIVATE)
    val isOnboarded = sharedPreferences.getBoolean("isOnboarded", false)
    val userId: Int = sharedPreferences.getInt(SHARED_PREFERENCES_USER_ID, 0)
    val startDestination = if (isOnboarded) HomeDestination.routeWithEffectiveArgs(userId) else OnboardingDestination.route

    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {

        composable(
            route = HomeDestination.routeWithArgs,
            arguments = listOf(
                navArgument(HomeDestination.ARG_USER_ID) {type = NavType.IntType}
            )
        ) {
            HomeScreen(
                navController = navController
            )
        }

        composable(
            route = UserDestination.routeWithArgs,
            arguments = listOf(navArgument(UserDestination.ARG_USER_ID) {
                type = NavType.IntType
            })
        ) {
            UserScreen(
                navigateBack = { navController.popBackStack()}
            )
        }

        composable(route = OnboardingDestination.route) {
            OnBoardingScreen(navigateToHome = { userId ->
                navController.navigate(HomeDestination.routeWithEffectiveArgs(userId)) {
                    popUpTo(OnboardingDestination.route) {
                        inclusive = true
                    }
                }
            })
        }

    }
}