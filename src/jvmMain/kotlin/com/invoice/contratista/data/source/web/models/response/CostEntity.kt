package com.invoice.contratista.data.source.web.models.response

import com.invoice.contratista.data.source.web.models.response.event.Modified


data class CostEntity(
    val id: String,
    val unitCost: Double,
    val quantity: Int,
    var date: Modified?,
    var vendor: VendorEntity?
)