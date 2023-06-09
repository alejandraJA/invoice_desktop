package com.invoice.contratista.data.source.web.models.response.event

data class Reserved(
    val dateExpiry: String,
    val id: String,
    val price: Price,
    val product: Product
)