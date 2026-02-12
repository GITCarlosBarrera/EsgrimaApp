package org.example.project.ui.user.poule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.component.BackButton
import org.example.project.component.CustomOutlinedTextField
import org.example.project.model.CompetitionStore
import org.example.project.model.Poule

@Composable
fun ManagePoulesScreen(
    onBack: () -> Unit,
    refereeName: String
) {
    val myPoules = CompetitionStore.competitions.flatMap { it.poules }
        .filter { it.referee.name.equals(refereeName, ignoreCase = true) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackButton(
                onBack = onBack,
                modifier = Modifier.align(Alignment.TopStart)
            )

            Column {
                Spacer(modifier = Modifier.height(90.dp))
                if (myPoules.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No tienes poules asignadas hoy.", color = Color.Gray)
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                ) {
                    items(myPoules) { poule ->
                        PouleControlCard(poule)
                    }
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                        androidx.compose.material3.Button(
                            onClick = { onBack() }, // Por ahora solo vuelve atrás
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = "Finalizar y Guardar",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(40.dp)) // Espacio extra abajo
                    }
                }
            }
        }
    }
}

@Composable
fun PouleControlCard(poule: Poule) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Poule #${poule.id} - Registro de Puntos",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Por cada tirador, creamos una "Fila de Asaltos"
            poule.fencers.forEach { fencerPrincipal ->
                Text(
                    text = "Puntos de ${fencerPrincipal.name}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    poule.fencers.forEach { oponente ->
                        if (fencerPrincipal.name != oponente.name) {
                            AsaltoCell(
                                opponentName = oponente.name,
                                onScoreEntered = { puntos ->
                                    fencerPrincipal.score += puntos
                                }
                            )
                        } else {
                            // Celda bloqueada con estilo coherente
                            Surface(
                                modifier = Modifier.size(width = 120.dp, height = 60.dp),
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                                shape = RoundedCornerShape(16.dp),
                                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text("—", color = Color.LightGray)
                                }
                            }
                        }
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp)
            }
        }
    }
}

@Composable
fun AsaltoCell(
    opponentName: String,
    onScoreEntered: (Int) -> Unit
) {
    var textValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .width(150.dp) // Un poco más ancho para que tu OutlinedTextField luzca bien
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "vs $opponentName",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        CustomOutlinedTextField(
            text = textValue,
            onTextChange = { newValue ->
                // Solo números y máximo 2 dígitos (puntuación de esgrima)
                if (newValue.all { it.isDigit() } && newValue.length <= 2) {
                    val oldScore = textValue.toIntOrNull() ?: 0
                    val newScore = newValue.toIntOrNull() ?: 0

                    textValue = newValue
                    onScoreEntered(newScore - oldScore)
                }
            },
            placeholder = "0",
            icon = Icons.Default.Edit, // Icono de edición o Numbers
            modifier = Modifier.fillMaxWidth()
        )
    }
}