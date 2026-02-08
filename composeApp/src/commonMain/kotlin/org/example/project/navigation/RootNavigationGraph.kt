package org.example.project.navigation

import androidx.compose.runtime.*
import org.example.project.MainController
import org.example.project.ui.auth.LoginScreen
import org.example.project.ui.auth.RegisterScreen

@Composable
fun RootNavigationGraph() {
    var currentScreen by remember { mutableStateOf<RootScreen>(RootScreen.Login) }

    when (currentScreen) {
        RootScreen.Login -> {
            LoginScreen(
                onLoginSuccess = {
                    // Al loguearse, cambiamos la variable a Main
                    currentScreen = RootScreen.Main
                },
                onNavigateRegister = {
                    currentScreen = RootScreen.Register
                }
            )
        }
        RootScreen.Register -> {
            RegisterScreen(
                onRegisterSuccess = {
                    currentScreen = RootScreen.Main
                },
                onNavigateLogin = {
                    currentScreen = RootScreen.Login
                }
            )
        }
        RootScreen.Main -> {
            MainController()
        }
    }
}