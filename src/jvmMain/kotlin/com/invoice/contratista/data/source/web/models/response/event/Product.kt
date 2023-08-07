package com.invoice.contratista.data.source.web.models.response.event

data class Product(
    val id: String,
    val modified: String,
    val name: String,
    val priceEntities: List<PriceEntity>,
    val productBase: ProductBase,
    val taxEntities: List<TaxEntity>
)