package com.invoice.contratista.data.source.web.models.response

import com.invoice.contratista.data.source.web.models.response.event.Product


data class
ProductInventoryModel(
    val id: String,
    val quantity: Int,
    var modified: String?,
    var product: Product,
)