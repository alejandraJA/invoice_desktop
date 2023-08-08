package com.invoice.contratista.data.source.web.models.response.event

import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel

data class Reserved(
    val id: String,
    val dateExpiry: String,
    val price: Price,
//    val product: Product
    val inventory: ProductInventoryModel
)