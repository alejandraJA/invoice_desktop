package com.invoice.contratista.data.source.web.models.response

import com.invoice.contratista.data.source.web.models.response.event.Modified
import com.invoice.contratista.data.source.web.models.response.event.Product


data class
ProductInventoryModel(
    val id: String,
    val quantity: Int,
    var modified: Modified?,
    var product: Product,
    var costEntities: MutableList<CostEntity>
)