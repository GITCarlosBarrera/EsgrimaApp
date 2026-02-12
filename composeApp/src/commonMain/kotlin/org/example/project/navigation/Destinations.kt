package org.example.project.navigation

import org.example.project.model.Competition

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
    data class ManageFencersRefereesPistes(val competition: Competition) : MainDestination()
    object Clasifications : MainDestination()

    // USER Screens
    data class ManagePoules(val refereeName: String) : MainDestination()
    data class ManageAssaults(val refereeName: String) : MainDestination()
}