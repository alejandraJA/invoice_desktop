package com.invoice.contratista.ui.section.budget

import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.event.BudgetEntity
import com.invoice.contratista.data.source.web.models.response.event.Part
import com.invoice.contratista.service.BudgetService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BudgetViewModel : KoinComponent {

    private val service: BudgetService by inject()

    val part = mutableStateOf<Part?>(null)
    val inventory = mutableStateOf<ProductInventoryModel?>(null)
    fun setPart(part: Part) {
        this.part.value = part
        inventory.value = part.reserved.inventory
    }

    suspend fun getBudget(id: String, onSuccess: (BudgetEntity) -> Unit) {
        service.getById(id, onSuccess, {})
    }

    suspend fun addPart(budgetId: String, onSuccess: (List<Part>) -> Unit) {
        service.createPart(budgetId, onSuccess) { e: String -> }
    }
}