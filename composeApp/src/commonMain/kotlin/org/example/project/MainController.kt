package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.example.project.home.HomeScreen
import org.example.project.navigation.MainDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainController() {
    var currentDestination by remember { mutableStateOf<MainDestination>(MainDestination.Home) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Pantallas
    val menuItems = listOf(
        MainDestination.Home to "Home",
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "Esgrima App", modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
                HorizontalDivider()

                menuItems.forEach { (destino, titulo) ->
                    NavigationDrawerItem(
                        label = { Text(titulo) },
                        selected = currentDestination == destino,
                        onClick = {
                            currentDestination = destino
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("EsgrimaApp") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (currentDestination) {
                    is MainDestination.Home -> HomeScreen()
                }
            }
        }
    }
}