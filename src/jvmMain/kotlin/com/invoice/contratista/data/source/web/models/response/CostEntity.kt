package com.invoice.contratista.data.source.web.models.response


data class CostEntity(
    val id: String,
    val unitCost: Double,
    val quantity: Int,
    var date: String,
    var vendor: VendorEntity?
)