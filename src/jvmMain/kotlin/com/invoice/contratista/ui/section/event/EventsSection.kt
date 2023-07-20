package com.invoice.contratista.ui.section.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.invoice.contratista.ui.section.budget.BudgetSection
import com.invoice.contratista.ui.theme.ModifierPaddingScreen2

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EventsSection() = Column(
    modifier = ModifierPaddingScreen2.fillMaxHeight().fillMaxWidth(),
) {
    val viewModel = remember{ EventViewModel() }

    if (viewModel.addEventIndicator.value) {
        EventAdd()
    } else {
        if (viewModel.event.value == null) EventLazy(viewModel)
        else {
            if (viewModel.budget.value == null)
                EventSection(viewModel)
            else BudgetSection(viewModel)
        }
    }
}