package com.invoice.contratista.data.source.web.models

data class Part(
    val id: String,
    val number: Int,
    var quantity: Int,
    var discount: Double,
    var reserved: Reserved
)