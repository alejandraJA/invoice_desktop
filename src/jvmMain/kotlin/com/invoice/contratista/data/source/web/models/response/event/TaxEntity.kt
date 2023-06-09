package com.invoice.contratista.data.source.web.models.response.event

data class TaxEntity(
    val factor: String,
    val id: String,
    val localTax: Boolean,
    val rate: Double,
    val type: String,
    val withholding: Boolean
)