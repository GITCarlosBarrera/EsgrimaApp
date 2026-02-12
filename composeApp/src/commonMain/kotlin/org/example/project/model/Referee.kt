package org.example.project.model

import kotlin.time.Clock

data class Referee(
    val id: String = Clock.System.now().toString(),
    val name: String,
    val numFederation: String
)
