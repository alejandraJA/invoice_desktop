package com.invoice.contratista.data.source.web.models

data class Tax(
    val factor: String,
    val id: String,
    val localTax: Boolean,
    val rate: Double,
    val type: String,
    val withholding: Boolean
)