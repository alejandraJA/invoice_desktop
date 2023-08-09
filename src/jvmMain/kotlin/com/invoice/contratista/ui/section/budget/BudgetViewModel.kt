package com.invoice.contratista.ui.section.budget

import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.event.BudgetEntity
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.service.BudgetService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BudgetViewModel : KoinComponent {

    private val service: BudgetService by inject()

    val part = mutableStateOf<PartEntity?>(null)
    val inventory = mutableStateOf<ProductInventoryModel?>(null)
    fun setPart(partEntity: PartEntity) {
        part.value = partEntity
        inventory.value = partEntity.reserved.inventory
    }

    suspend fun getBudget(id: String, onSuccess:(BudgetEntity) -> Unit) {
        service.getById(id, onSuccess, {})
    }
}