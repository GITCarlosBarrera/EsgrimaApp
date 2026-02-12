package org.example.project.ui.admin.competition

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // Corregido el import de Color
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
fun CreateCompetitionScreen(
    onBack: () -> Unit
) {
    var competitionName by remember { mutableStateOf("") }
    var competitionDate by remember { mutableStateOf("") }
    var tracksCount by remember { mutableStateOf("1") }

    val tempFencers = remember { mutableStateListOf<Fencer>() }
    val tempReferees = remember { mutableStateListOf<Referee>() }

    var showFencerDialog by remember { mutableStateOf(false) }
    var showRefereeDialog by remember { mutableStateOf(false) }

    var showErrorFencer by remember { mutableStateOf(false) }
    var showErrorReferee by remember { mutableStateOf(false) }

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
                Spacer(modifier = Modifier.fillMaxWidth().padding(46.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp)
                        .verticalScroll(rememberScrollState()), // Añadido para que no se corte al añadir muchos
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "New Competition",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = "Nombre de la competición", // Corregido el texto de la etiqueta
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        CustomOutlinedTextField(
                            text = competitionName,
                            onTextChange = { competitionName = it },
                            placeholder = "Competition Name",
                            icon = Icons.Default.EmojiEvents
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Column(
                                modifier = Modifier.weight(1f), // Añadido weight para que se repartan el ancho
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Text(
                                    text = "Fecha",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                CustomOutlinedTextField(
                                    text = competitionDate,
                                    onTextChange = { competitionDate = it },
                                    placeholder = "Fecha [DD/MM/YYYY]",
                                    icon = Icons.Default.Event
                                )
                            }
                            Column(
                                modifier = Modifier.weight(0.6f),
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
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
                        }

                        // Tiradores
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
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir Tirador")
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
                                        Text(
                                            text = "${fencer.score} points",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                    IconButton(onClick = { tempFencers.remove(fencer) }) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "Borrar",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            }
                        }

                        // Arbitros
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
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir Árbitro")
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
                                        Text(
                                            text = "Fed: ${referee.numFederation}",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                    IconButton(onClick = { tempReferees.remove(referee) }) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "Borrar",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            }
                        }
                    }

                    if (showErrorFencer) {
                        Text(
                            text = "Los tiradores tienen que ser pares",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    if (showErrorReferee) {
                        Text(
                            text = "No puede haber más de un árbitro por cada dos tiradores",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    Button(
                        onClick = {
                            // Solo calculamos errores si hay contenido, para que no salgan en rojo al abrir la pantalla
                            val hasFencers = tempFencers.isNotEmpty()
                            val hasReferees = tempReferees.isNotEmpty()

                            // Condición 1: Tiradores deben ser pares y la lista no estar vacía
                            val isFencerCountValid = hasFencers && (tempFencers.size % 2 == 0)

                            // Condición 2: Árbitros deben ser entre 1 y la mitad de los tiradores (según tu lógica anterior)
                            // Si quieres que sea "al menos uno pero no más de la mitad":
                            val isRefereeCountValid = hasReferees && (tempReferees.size <= tempFencers.size / 2)

                            // MOSTRAR ERRORES: Solo si el usuario ha escrito algo pero está mal
                            showErrorFencer = hasFencers && !isFencerCountValid
                            showErrorReferee = hasReferees && !isRefereeCountValid

                            // 2. Si todo es correcto (y no están vacíos), guardamos
                            if (isFencerCountValid && isRefereeCountValid && competitionDate.isNotBlank() && competitionName.isNotBlank()) {
                                val newCompetition = Competition(
                                    name = competitionName,
                                    date = competitionDate,
                                    numPistes = tracksCount.toIntOrNull() ?: 1,
                                    fencers = tempFencers.toList(),
                                    referees = tempReferees.toList()
                                )
                                CompetitionStore.addCompetition(newCompetition)
                                onBack()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Crear Competición",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }

                // Dialogo Tiradores
                if (showFencerDialog) {
                    AddFencerDialog(
                        onDismiss = { showFencerDialog = false },
                        onConfirm = { name, score ->
                            tempFencers.add(Fencer(name = name, score = score))
                            showFencerDialog = false
                        }
                    )
                }

                // Dialogo Arbitros
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
}

// Mantengo tu función de diálogo tal cual, solo asegúrate de que esté en este archivo
@Composable
fun AddFencerDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, Int) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Fencer") },
        text = {
            CustomOutlinedTextField(
                text = name,
                onTextChange = { name = it },
                placeholder = "Fencer Name",
                icon = Icons.Default.Person
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (name.isNotBlank()) {
                        onConfirm(name, score.toIntOrNull() ?: 0)
                    }
                }
            ) { Text("Add") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
fun AddRefereeDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var fedNum by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Referee") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CustomOutlinedTextField(
                    text = name,
                    onTextChange = { name = it },
                    placeholder = "Referee Name",
                    icon = Icons.Default.Person
                )
                CustomOutlinedTextField(
                    text = fedNum,
                    onTextChange = { fedNum = it },
                    placeholder = "Federation Number",
                    icon = Icons.Default.ConfirmationNumber // O Numbers
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (name.isNotBlank()) {
                    onConfirm(name, fedNum)
                }
            }) { Text("Add") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}