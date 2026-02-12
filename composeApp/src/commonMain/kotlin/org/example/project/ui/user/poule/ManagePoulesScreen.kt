package org.example.project.ui.user.poule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.component.BackButton
import org.example.project.model.CompetitionStore

@Composable
fun ManagePoulesScreen(
    onBack: () -> Unit,
    refereeName: String
) {
    val myCompetitions = CompetitionStore.competitions.filter { competition ->
        competition.referees.any { it.name.equals(refereeName, ignoreCase = true)}
    }

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
                if (myCompetitions.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No tienes competiciones asignadas hoy.", color = Color.Gray)
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                ) {
                    items(myCompetitions) { comp ->
                        Text(text = "${comp.name}")
                    }
                }
            }
        }
    }
}