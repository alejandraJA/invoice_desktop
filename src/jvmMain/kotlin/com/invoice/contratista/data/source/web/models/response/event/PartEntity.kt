package com.invoice.contratista.data.source.web.models.response.event

data class PartEntity(
    var discount: Double,
    val id: String,
    val number: Int,
    val quantity: Int,
    val reserved: Reserved
)