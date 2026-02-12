package org.example.project.model

import kotlin.time.Clock

data class Fencer(
    val id: String = Clock.System.now().toString(),
    val name: String,
    var score: Int = 0
)
