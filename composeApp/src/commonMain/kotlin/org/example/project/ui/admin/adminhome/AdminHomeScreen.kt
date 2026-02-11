package org.example.project.ui.admin.adminhome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import esgrimaapp.composeapp.generated.resources.Res
import esgrimaapp.composeapp.generated.resources.esgrima_app_logo
import org.example.project.components.ElevatedButtonCard
import org.example.project.navigation.MainDestination
import org.jetbrains.compose.resources.painterResource

@Composable
fun AdminHomeScreen(
    onNavigate: (MainDestination) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ElevatedButtonCard(
                    title = "Crear Competicion",
                    subtitle = "",
                    icon = painterResource(Res.drawable.esgrima_app_logo),
                    iconColor = Color.Blue,
                    onClick = { onNavigate(MainDestination.Home) }
                )
            }
        }
    }
}