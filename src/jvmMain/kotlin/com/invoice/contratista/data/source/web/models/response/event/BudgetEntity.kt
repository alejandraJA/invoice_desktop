package com.invoice.contratista.data.source.web.models.response.event

data class BudgetEntity(
    val conditions: String,
    val date: String,
    val id: String,
    val number: Int,
    val partEntities: List<PartEntity>,
    val status: String
)