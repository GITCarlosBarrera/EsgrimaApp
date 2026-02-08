package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.project.navigation.RootNavigationGraph

@Composable
fun App() {
    MaterialTheme {
        RootNavigationGraph()
    }
}