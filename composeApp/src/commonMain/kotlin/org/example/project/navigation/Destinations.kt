package org.example.project.navigation

sealed class RootScreen {
    object Login : RootScreen()
    object Register : RootScreen()
    object Main : RootScreen()
}

sealed class MainDestination {
    object Home : MainDestination()
}