package org.example.project.ui.admin.competition

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.component.BackButton
import org.example.project.model.Competition
import org.example.project.model.CompetitionStore

@Composable
fun ClasificationsScreen(
    onBack: () -> Unit
) {
    val competitionList = CompetitionStore.competitions

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackButton(
                onBack = onBack,
                modifier = Modifier.align(Alignment.TopStart)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(90.dp))

                Text(
                    text = "Clasificaciones Globales",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                if (competitionList.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No hay datos para mostrar", color = Color.Gray)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 32.dp)
                    ) {
                        items(competitionList) { competition ->
                            ClassificationCard(competition)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClassificationCard(competition: Competition) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Cabecera: Nombre de la competición
            Text(
                text = competition.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )

            // Árbitros
            val refereesNames = competition.referees.joinToString(", ") { it.name }
            Text(
                text = "Árbitros: $refereesNames",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            )

            // Ranking de Tiradores (Ordenados por score)
            val sortedFencers = competition.fencers.sortedByDescending { it.score }

            sortedFencers.forEachIndexed { index, fencer ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Medalla o número de posición
                        Text(
                            text = "${index + 1}.",
                            fontWeight = FontWeight.Bold,
                            color = if (index == 0) Color(0xFFFFD700) else Color.Gray, // Dorado para el 1º
                            modifier = Modifier.width(28.dp)
                        )
                        Text(
                            text = fencer.name,
                            fontWeight = if (index == 0) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    }

                    Text(
                        text = "${fencer.score} pts",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}