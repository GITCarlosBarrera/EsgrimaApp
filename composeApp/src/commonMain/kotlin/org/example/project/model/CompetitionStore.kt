package org.example.project.model

import androidx.compose.runtime.mutableStateListOf

object CompetitionStore {
    // Esta lista guardará las competiciones en la memoria RAM de la app
    val competitions = mutableStateListOf<Competition>()

    fun addCompetition(competition: Competition) {
        competitions.add(competition)
    }
}