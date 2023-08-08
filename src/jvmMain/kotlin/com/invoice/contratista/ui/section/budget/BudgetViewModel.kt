package com.invoice.contratista.ui.section.budget

import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.service.ProductService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BudgetViewModel : KoinComponent {
    private val service: ProductService by inject()

    val loadingDialogState = mutableStateOf(true)
    val errorState = mutableStateOf("")
    val inventory = mutableStateOf<ProductInventoryModel?>(null)
    val part = mutableStateOf<PartEntity?>(null)
    suspend fun findByProductId(partEntity: PartEntity) {
        part.value = partEntity
        service.findByProductId(partEntity.reserved.inventory.product.id, { inventoryModel ->
            loadingDialogState.value = false
            if (inventoryModel.product.productBase.costEntities.isNotEmpty())
                inventory.value = inventoryModel
        }, { errorState.value })
    }
}