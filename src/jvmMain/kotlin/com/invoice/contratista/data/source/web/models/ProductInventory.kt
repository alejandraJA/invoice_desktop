package com.invoice.contratista.data.source.web.models

data class ProductInventory(
    val id: String,
    val quantity: Int,
    var modified: String?,
    var product: Product,
)