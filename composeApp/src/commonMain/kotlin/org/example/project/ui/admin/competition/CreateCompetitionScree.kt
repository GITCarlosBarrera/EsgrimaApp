package org.example.project.ui.admin.competition

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.components.BackButton
import org.example.project.navigation.MainDestination

@Composable
fun CreateCompetitionScreen(
    onBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        // 1. BOTÓN DE VOLVER (Arriba a la izquierda)
        BackButton(
            onBack = onBack,
            modifier = Modifier.align(Alignment.TopStart)
        )

        // 2. CONTENIDO PRINCIPAL (Centrado)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp), // Margen lateral para el contenido
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Crear Competición",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Aquí irían tus campos de texto, etc.
            Text("Aquí puedes meter tu formulario centrado")
        }
    }
}