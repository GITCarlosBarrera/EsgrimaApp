package org.example.project.navigation

sealed class RootScreen {
    object Login : RootScreen()
    object Main : RootScreen()
}

sealed class MainDestination {
    // Home Screens
    object Home : MainDestination()

    // ADMIN Screens
    object CreateCompetition : MainDestination()
    object ManageCompetition : MainDestination()

    // USER Screens
}