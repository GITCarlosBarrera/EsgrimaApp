package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.example.project.home.AdminHomeScreen
import org.example.project.home.UserHomeScreen
import org.example.project.navigation.MainDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainController(role: UserRole) {
    var currentDestination by remember { mutableStateOf<MainDestination>(MainDestination.Home) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("EsgrimaApp") },
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (currentDestination) {
                MainDestination.Home -> {
                    if (role == UserRole.ADMIN) {
                        AdminHomeScreen(onNavigate = { currentDestination = it })
                    } else {
                        UserHomeScreen(onNavigate = { currentDestination = it })
                    }
                }
            }
        }
    }
}