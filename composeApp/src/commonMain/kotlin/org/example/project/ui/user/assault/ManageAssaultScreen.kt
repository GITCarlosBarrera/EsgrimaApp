package org.example.project.ui.user.assault

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.component.BackButton
import org.example.project.model.CompetitionStore
import org.example.project.model.Fencer

@Composable
fun ManageAssaultScreen(
    onBack: () -> Unit,
    refereeName: String
) {
    val myCompetitions = CompetitionStore.competitions.filter { comp ->
        comp.referees.any { it.name.equals(refereeName, ignoreCase = true) }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackButton(onBack = onBack, modifier = Modifier.padding(16.dp))
            Text(
                text = "Cuadro de Asaltos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(top = 36.dp),
                textAlign = TextAlign.Center
            )
            Column {
                Spacer(modifier = Modifier.height(80.dp))
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        items(myCompetitions) { competition ->
                            val topFencers = competition.fencers
                                .sortedByDescending { it.score }
                                .take(8)

                            Text(
                                text = "Competición: ${competition.name}",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            // El contenedor del árbol con scroll horizontal por si es muy ancho
                            Box(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                                TournamentBracket(topFencers)
                            }

                            HorizontalDivider(modifier = Modifier.padding(vertical = 32.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TournamentBracket(fencers: List<Fencer>) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // COLUMNA 1: CUARTOS DE FINAL
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            repeat(4) { i ->
                val f1 = fencers.getOrNull(i * 2)?.name ?: "---"
                val f2 = fencers.getOrNull(i * 2 + 1)?.name ?: "---"
                BracketMatch(f1, f2)
            }
        }

        BracketConnector()

        // COLUMNA 2: SEMIFINALES
        Column(verticalArrangement = Arrangement.spacedBy(80.dp)) {
            repeat(2) { BracketMatch("Ganador", "Ganador") }
        }

        BracketConnector()

        // COLUMNA 3: FINAL
        Column {
            BracketMatch("Ganador Sem. 1", "Ganador Sem. 2")
        }
    }
}

@Composable
fun BracketMatch(name1: String, name2: String) {
    Surface(
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(4.dp), // Bordes menos redondeados para un look más serio
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f)),
        color = Color.Blue.copy(alpha = 0.15f)
    ) {
        Column {
            Text(
                text = name1,
                modifier = Modifier.padding(8.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            HorizontalDivider(thickness = 0.5.dp)
            Text(
                text = name2,
                modifier = Modifier.padding(8.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun BracketConnector() {
    // Una simple línea o flecha para conectar las rondas
    Icon(
        imageVector = Icons.Default.ChevronRight,
        contentDescription = null,
        modifier = Modifier.padding(horizontal = 8.dp),
        tint = Color.Gray
    )
}