package com.invoice.contratista.data.source.web.models.response.event

data class PartEntity(
    var discount: Double,
    val id: String,
    val number: Int,
    var quantity: Int,
    val reserved: Reserved
)