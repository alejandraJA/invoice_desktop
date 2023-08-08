package com.invoice.contratista.ui.section.budget

import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.service.ProductService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BudgetViewModel : KoinComponent {

    val part = mutableStateOf<PartEntity?>(null)
    val inventory = mutableStateOf<ProductInventoryModel?>(null)
    fun setPart(partEntity: PartEntity) {
        part.value = partEntity
        inventory.value = partEntity.reserved.inventory
    }
}