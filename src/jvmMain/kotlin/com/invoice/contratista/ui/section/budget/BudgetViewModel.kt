package com.invoice.contratista.ui.section.budget

import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.ProductInventory
import com.invoice.contratista.data.source.web.models.Part
import com.invoice.contratista.service.BudgetService
import com.invoice.contratista.ui.section.event.EventViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BudgetViewModel : KoinComponent {

    private val service: BudgetService by inject()

    val part = mutableStateOf<Part?>(null)
    val inventory = mutableStateOf<ProductInventory?>(null)

    fun setPart(part: Part) {
        this.part.value = part
        inventory.value = part.reserved.inventory
    }

    suspend fun getBudget(eventViewModel: EventViewModel) {
        service.getById(eventViewModel.budget.value!!.id, onSuccess = { newBudget ->
            eventViewModel.budgetSelected.invoke(null)
            eventViewModel.budgetSelected.invoke(newBudget)
        }, onError = {})
    }

    suspend fun addPart(eventViewModel: EventViewModel) {
        service.createPart(eventViewModel.budget.value!!.id, onSuccess = { partList: List<Part> ->
            eventViewModel.listParts.value = partList
        }, onError = { e: String -> })
    }
}