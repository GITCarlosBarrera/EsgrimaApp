package org.example.project.model

import kotlin.time.Clock

data class Competition(
    val id: String = Clock.System.now().toString(),
    val name: String,
    val date: String,
    val numPistes: Int,
    val fencers: List<Fencer> = emptyList(),
    val referees: List<Referee> = emptyList()
)
