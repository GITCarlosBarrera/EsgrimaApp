package org.example.project.model

data class Poule(
    val id: Int,
    val referee: Referee,
    val fencers: List<Fencer>
)
