package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import esgrimaapp.composeapp.generated.resources.Res
import esgrimaapp.composeapp.generated.resources.logo_app_white
import org.example.project.home.UserHomeScreen
import org.example.project.navigation.MainDestination
import org.example.project.ui.admin.adminhome.AdminHomeScreen
import org.example.project.ui.admin.competition.CreateCompetitionScreen
import org.example.project.ui.admin.competition.ManageCompetitionScreen
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainController(
    role: UserRole,
    onLogout: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    var currentDestination by remember { mutableStateOf<MainDestination>(MainDestination.Home) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.logo_app_white),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                        Text(
                            text = "EsgrimaApp",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { showMenu = true}) {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = "Sesion",
                            tint = Color.White
                        )
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Cerrar sesión") },
                            onClick = {
                                showMenu = false
                                onLogout()
                            },
                            leadingIcon = {
                                Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
                            }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (currentDestination) {
                // RUTA: Homes
                MainDestination.Home -> {
                    if (role == UserRole.ADMIN) {
                        AdminHomeScreen(onNavigate = { currentDestination = it })
                    } else {
                        UserHomeScreen(onNavigate = { currentDestination = it })
                    }
                }

                // RUTA: Crear Competición
                MainDestination.CreateCompetition -> {
                    // Aquí llamas a tu pantalla (tienes que crearla)
                    CreateCompetitionScreen(
                        onBack = { currentDestination = MainDestination.Home }
                    )
                }

                // RUTA: Gestionar Competición
                MainDestination.ManageCompetition -> {
                    ManageCompetitionScreen(
                        onBack = { currentDestination = MainDestination.Home }
                    )
                }
            }
        }
    }
}