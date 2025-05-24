package com.example.android.littlelemon


interface Destinations {
    val route: String
}

object HomeDestination : Destinations {
    override val route: String
        get() = "Home"
}

object ProfileDestination: Destinations {
    override val route: String
        get() = "Profile"
}

object OnboardingDestination: Destinations {
    override val route: String
        get() = "Onboarding"
}
