package com.invoice.contratista.data.source.web.models

data class Product(
    val id: String,
    val name: String,
    val modified: String,
    val productBase: ProductBase,
    val priceEntity: Price,
    val taxEntities: List<Tax>,
    val costEntity: Cost
)