package com.invoice.contratista.data.source.web.models.response

data class Availability(
    val available: Int,
    val idInventory: String,
    val idProduct: String,
    val quantity: Int,
    val reserved: Int
)