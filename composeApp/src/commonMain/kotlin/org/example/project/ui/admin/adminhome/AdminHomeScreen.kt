package org.example.project.ui.admin.adminhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import esgrimaapp.composeapp.generated.resources.CrearComp_white
import esgrimaapp.composeapp.generated.resources.GestionarComp_white
import esgrimaapp.composeapp.generated.resources.Res
import esgrimaapp.composeapp.generated.resources.logo_app_white
import org.example.project.component.ElevatedButtonCard
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
                Surface(
                    modifier = Modifier.size(180.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7F),
                    shape = CircleShape
                ) {
                    Image(
                        painterResource(Res.drawable.logo_app_white),
                        contentDescription = null,
                        modifier = Modifier.padding(32.dp)
                    )
                }
            }
            item {
                ElevatedButtonCard(
                    title = "Crear Competicion",
                    subtitle = "Empieza una nueva competición",
                    icon = painterResource(Res.drawable.CrearComp_white),
                    iconColor = Color.Red,
                    onClick = { onNavigate(MainDestination.CreateCompetition) }
                ) }
            item {
                ElevatedButtonCard(
                    title = "Gestionar Competicion",
                    subtitle = "Modifica competiciones ya existentes",
                    icon = painterResource(Res.drawable.GestionarComp_white),
                    iconColor = Color.Blue,
                    onClick = { onNavigate(MainDestination.ManageCompetition) }
                )
            }
        }
    }
}