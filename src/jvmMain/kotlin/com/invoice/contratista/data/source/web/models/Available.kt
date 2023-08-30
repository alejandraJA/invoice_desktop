package com.invoice.contratista.data.source.web.models

data class Available(
    val id: String,
    val modified: String,
    val productId: String,
    val quantity: Int,
    val userId: String
)