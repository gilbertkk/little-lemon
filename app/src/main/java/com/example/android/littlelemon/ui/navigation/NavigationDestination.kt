package com.example.android.littlelemon.ui.navigation

interface NavigationDestination {
    val route: String
}
object HomeDestination : NavigationDestination {
    override val route: String = "home"
    const val ARG_USER_ID = "userId"
    val routeWithArgs = "$route/{$ARG_USER_ID}"

    fun routeWithEffectiveArgs(userId: Int) : String {
        return "$route/$userId"
    }
}


object ProfileDestination : NavigationDestination {
    override val route = "user"
    const val ARG_USER_ID = "userId"
    val routeWithArgs = "$route/{$ARG_USER_ID}"

    fun routeWithEffectiveArgs(userId: Int) : String {
        return "$route/$userId"
    }
}

object OnboardingDestination: NavigationDestination {
    override val route: String
        get() = "Onboarding"
}

