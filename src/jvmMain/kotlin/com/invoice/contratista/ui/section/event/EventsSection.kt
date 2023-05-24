package com.invoice.contratista.ui.section.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.invoice.contratista.ui.theme.ModifierPaddingScreen

@Composable
fun EventSection() = ElevatedCard(
    modifier = ModifierPaddingScreen.fillMaxHeight()
) {
    Column(modifier = ModifierPaddingScreen) {
        Text(text = "Open Events", style = MaterialTheme.typography.titleLarge)
    }
}
