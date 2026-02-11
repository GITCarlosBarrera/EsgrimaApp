package org.example.project.navigation

import androidx.compose.runtime.*
import org.example.project.MainController
import org.example.project.UserRole
import org.example.project.ui.auth.LoginScreen

@Composable
fun RootNavigationGraph() {
    var currentScreen by remember { mutableStateOf<RootScreen>(RootScreen.Login) }
    var userRole by remember { mutableStateOf<UserRole?>(null) }

    when (currentScreen) {
        RootScreen.Login -> {
            LoginScreen(
                onLoginSuccess = { role ->
                    userRole = role
                    currentScreen = RootScreen.Main
                }
            )
        }
        RootScreen.Main -> {
            MainController(role = userRole ?: UserRole.USER)
        }
    }
}