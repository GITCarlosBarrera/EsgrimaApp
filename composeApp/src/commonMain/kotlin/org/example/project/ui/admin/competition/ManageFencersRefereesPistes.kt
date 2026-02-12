package org.example.project.ui.admin.competition

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.component.BackButton
import org.example.project.component.CustomOutlinedTextField
import org.example.project.model.Competition
import org.example.project.model.CompetitionStore
import org.example.project.model.Fencer
import org.example.project.model.Referee

@Composable
fun ManageFencersRefereesPistesScreen(
    onBack: () -> Unit,
    competition: Competition
) {
    // 1. Inicializamos los estados con los datos de la competición recibida
    var tracksCount by remember { mutableStateOf(competition.numPistes.toString()) }

    // Importante: Llenamos las listas con los datos actuales
    val tempFencers = remember { mutableStateListOf<Fencer>().apply { addAll(competition.fencers) } }
    val tempReferees = remember { mutableStateListOf<Referee>().apply { addAll(competition.referees) } }
    val numPistes by remember { mutableStateOf(competition.numPistes) }

    var showFencerDialog by remember { mutableStateOf(false) }
    var showRefereeDialog by remember { mutableStateOf(false) }

    var showErrorFencer by remember { mutableStateOf(false) }
    var showErrorReferee by remember { mutableStateOf(false) }
    var showErrorPistes by remember { mutableStateOf(false) }

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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Editar: ${competition.name}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Campo de Pistas (Único editable de la cabecera)
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Numero de pistas",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        CustomOutlinedTextField(
                            text = tracksCount,
                            onTextChange = { if (it.all { c -> c.isDigit() }) tracksCount = it },
                            placeholder = "Tracks",
                            icon = Icons.Default.Numbers
                        )
                    }

                    // Sección Tiradores (Reutilizando tu lógica)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tiradores (${tempFencers.size})",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        IconButton(
                            onClick = { showFencerDialog = true },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
                        }
                    }

                    tempFencers.forEach { fencer ->
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(text = fencer.name, fontWeight = FontWeight.Bold)
                                    Text(text = "${fencer.score} pts", color = MaterialTheme.colorScheme.primary)
                                }
                                IconButton(onClick = { tempFencers.remove(fencer) }) {
                                    Icon(Icons.Default.Delete, "Borrar", tint = MaterialTheme.colorScheme.error)
                                }
                            }
                        }
                    }

                    // Sección Árbitros
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Árbitros (${tempReferees.size})",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        IconButton(
                            onClick = { showRefereeDialog = true },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                        ) {
                            Icon(imageVector = Icons.Default.Add, "Añadir")
                        }
                    }

                    tempReferees.forEach { referee ->
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(text = referee.name, fontWeight = FontWeight.Bold)
                                    Text(text = "Fed: ${referee.numFederation}", color = MaterialTheme.colorScheme.primary)
                                }
                                IconButton(onClick = { tempReferees.remove(referee) }) {
                                    Icon(Icons.Default.Delete, "Borrar", tint = MaterialTheme.colorScheme.error)
                                }
                            }
                        }
                    }

                    // Errores
                    if (showErrorFencer) Text("Tiene que haber mas de 1 tirador", color = MaterialTheme.colorScheme.error)
                    if (showErrorReferee) Text("Máximo un árbitro por cada dos tiradores", color = MaterialTheme.colorScheme.error)
                    if (showErrorPistes) {
                        Text(
                            text = "No hay suficientes pistas para tantos arbitros",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    // BOTÓN GUARDAR CAMBIOS
                    Button(
                        onClick = {
                            val isFencerCountValid = tempFencers.size > 1
                            val isRefereeCountValid = tempReferees.isNotEmpty() && (tempReferees.size <= tempFencers.size / 2)
                            val isPistesCountValid = numPistes >= tempReferees.size


                            showErrorFencer = !isFencerCountValid
                            showErrorReferee = !isRefereeCountValid
                            showErrorPistes = !isPistesCountValid

                            if (isFencerCountValid && isRefereeCountValid && isPistesCountValid) {
                                // Actualizamos la competición en el Store
                                val newGeneratedPoules = CompetitionStore.generatePoules(
                                    tempFencers.toList(),
                                    tempReferees.toList()
                                )

                                val updatedCompetition = competition.copy(
                                    numPistes = tracksCount.toIntOrNull() ?: 1,
                                    fencers = tempFencers.toList(),
                                    referees = tempReferees.toList(),
                                    poules = newGeneratedPoules
                                )

                                // Buscamos el índice y reemplazamos
                                val index = CompetitionStore.competitions.indexOf(competition)
                                if (index != -1) {
                                    CompetitionStore.competitions[index] = updatedCompetition
                                }

                                onBack()
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Guardar Cambios", fontWeight = FontWeight.Bold, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }

            // Reutilizamos los diálogos que ya tienes definidos
            if (showFencerDialog) {
                AddFencerDialog(
                    onDismiss = { showFencerDialog = false },
                    onConfirm = { name, score ->
                        tempFencers.add(Fencer(name = name, score = score))
                        showFencerDialog = false
                    }
                )
            }

            if (showRefereeDialog) {
                AddRefereeDialog(
                    onDismiss = { showRefereeDialog = false },
                    onConfirm = { name, fedNum ->
                        tempReferees.add(Referee(name = name, numFederation = fedNum))
                        showRefereeDialog = false
                    }
                )
            }
        }
    }
}