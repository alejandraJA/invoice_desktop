package com.invoice.contratista.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.invoice.contratista.ui.section.event.EventsSection

@Composable
fun HomeScreen() = Column {
    EventsSection()
}