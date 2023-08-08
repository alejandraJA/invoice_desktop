package com.invoice.contratista.data.source.web.models.response.event

import com.invoice.contratista.data.source.web.models.response.CostEntity

data class Product(
    val id: String,
    val name: String,
    val modified: String,
    val productBase: ProductBase,
    val priceEntity: PriceEntity,
    val taxEntities: List<TaxEntity>,
    val costEntity: CostEntity
)