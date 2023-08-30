package com.invoice.contratista.data.source.web.models

data class ProductBase(
    val description: String,
    val id: String,
    val productKey: String,
    val sku: String,
    val taxIncluded: Boolean,
    val taxability: String,
    val unitKey: String,
    val unitName: String,
    val type: Boolean,
    val priceEntities: MutableList<Price>,
    val costEntities: MutableList<Cost>,
)