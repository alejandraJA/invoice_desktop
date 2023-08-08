package com.invoice.contratista.data.source.web.models.response.event

data class PartEntity(
    val id: String,
    val number: Int,
    var quantity: Int,
    var discount: Double,
    var reserved: Reserved
)