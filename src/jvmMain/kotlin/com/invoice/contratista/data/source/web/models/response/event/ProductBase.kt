package com.invoice.contratista.data.source.web.models.response.event

data class ProductBase(
    val description: String,
    val id: String,
    val productKey: String,
    val sku: String,
    val taxIncluded: Boolean,
    val taxability: String,
    val unitKey: String,
    val unitName: String
)