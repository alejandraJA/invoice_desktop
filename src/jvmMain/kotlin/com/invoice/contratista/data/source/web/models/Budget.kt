package com.invoice.contratista.data.source.web.models

data class Budget(
    val id: String,
    val number: Int,
    val date: String,
    val conditions: String,
    val status: String,
    val partEntities: List<Part>,
)