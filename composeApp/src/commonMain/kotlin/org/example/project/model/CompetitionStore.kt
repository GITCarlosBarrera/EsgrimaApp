package org.example.project.model

import androidx.compose.runtime.mutableStateListOf

object CompetitionStore {
    // Esta lista guardará las competiciones en la memoria RAM de la app
    val competitions = mutableStateListOf<Competition>()

    init {
        // --- DATOS DE PRUEBA (MOCK DATA) ---

        // 1. Creamos 8 Tiradores
        val mockFencers = listOf(
            Fencer(name = "Carlos Sainz"),
            Fencer(name = "Fernando Alonso"),
            Fencer(name = "Marc Márquez"),
            Fencer(name = "Alexia Putellas"),
            Fencer(name = "Rafa Nadal"),
            Fencer(name = "Pau Gasol"),
            Fencer(name = "Carolina Marín"),
            Fencer(name = "Ilia Topuria")
        )

        // 2. Creamos 2 Árbitros (Usa estos nombres para hacer Login)
        // Recuerda: para entrar como ellos, el usuario y pass debe ser el mismo (ej: "Pepe" / "Pepe")
        val mockReferees = listOf(
            Referee(name = "Pepe", numFederation = "FED-001"),
            Referee(name ="Juan", numFederation ="FED-002")
        )

        // 3. Generamos las Poules usando tu lógica automática
        val testPoules = generatePoules(mockFencers, mockReferees)

        // 4. Creamos la competición de prueba
        val testCompetition = Competition(
            name = "Copa del Rey - Test",
            date = "12/02/2026",
            numPistes = 4,
            fencers = mockFencers,
            referees = mockReferees,
            poules = testPoules
        )

        // 5. La añadimos a la lista
        competitions.add(testCompetition)
    }

    fun addCompetition(competition: Competition) {
        competitions.add(competition)
    }

    fun isRefereeRegistered(name: String): Boolean {
        return competitions.any { competition ->
            competition.referees.any { referee ->
                referee.name.equals(name, ignoreCase = true)
            }
        }
    }

    fun generatePoules(fencers: List<Fencer>, referees: List<Referee>): List<Poule> {
        if (referees.isEmpty() || fencers.isEmpty()) return emptyList()

        // 1. Creamos listas vacías para cada árbitro
        val groups = List(referees.size) { mutableListOf<Fencer>() }

        // 2. Reparto equitativo (Cíclico)
        // Esto soluciona tu ejemplo: 8 tiradores / 3 árbitros -> 3, 3, 2
        fencers.forEachIndexed { index, fencer ->
            val refereeIndex = index % referees.size
            groups[refereeIndex].add(fencer)
        }

        // 3. Empaquetamos todo en objetos Poule
        return referees.mapIndexed { index, referee ->
            Poule(
                id = index + 1,
                referee = referee,
                fencers = groups[index]
            )
        }
    }
}