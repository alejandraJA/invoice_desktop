@file:OptIn(ExperimentalMaterialApi::class)

package com.invoice.contratista.ui.section.event

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.invoice.contratista.data.source.web.models.response.event.BudgetEntity
import com.invoice.contratista.data.source.web.models.response.event.EventModel
import com.invoice.contratista.sys.domain.repository.component.EventComponent
import com.invoice.contratista.ui.section.budget.BudgetSection
import com.invoice.contratista.ui.theme.ModifierPaddingScreen2

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EventsSection() = Column(
    modifier = ModifierPaddingScreen2.fillMaxHeight().fillMaxWidth(),
) {
    val event = remember { mutableStateOf<EventModel?>(null) }
    val addEvent = remember { mutableStateOf(false) }
    val budget = remember { mutableStateOf<BudgetEntity?>(null) }
    val budgetSelected = { budgetEntity: BudgetEntity? ->
        budget.value = budgetEntity
    }
    val eventComponent = EventComponent()
    if (addEvent.value) {
        EventAdd()
    } else {
        if (event.value == null) EventLazy(
            eventComponent,
            eventSelected = { event.value = it },
            addEvent = { addEvent.value = true }
        )
        else {
            if (budget.value == null) EventSection(event.value!!, budgetSelected)
            else BudgetSection(budget.value!!, budgetSelected, event.value!!.customerEntity)
        }
    }

}