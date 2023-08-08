package com.invoice.contratista.ui.section.budget

import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import org.koin.core.component.KoinComponent

class BudgetViewModel : KoinComponent {

    val part = mutableStateOf<PartEntity?>(null)
    val inventory = mutableStateOf<ProductInventoryModel?>(null)
    fun setPart(partEntity: PartEntity) {
        part.value = partEntity
        inventory.value = partEntity.reserved.inventory
    }
}