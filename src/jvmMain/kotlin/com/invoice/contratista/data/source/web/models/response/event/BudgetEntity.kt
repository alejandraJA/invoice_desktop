package com.invoice.contratista.data.source.web.models.response.event

data class BudgetEntity(
    val id: String,
    val number: Int,
    val date: String,
    val conditions: String,
    val status: String,
    val partEntities: List<PartEntity>,
)