package com.invoice.contratista.data.source.web.models.response.event

import com.invoice.contratista.data.source.web.models.response.CostEntity

data class ProductBase(
    val description: String,
    val id: String,
    val productKey: String,
    val sku: String,
    val taxIncluded: Boolean,
    val taxability: String,
    val unitKey: String,
    val unitName: String,
    val priceEntities: MutableList<PriceEntity>,
    val costEntities: MutableList<CostEntity>,
)