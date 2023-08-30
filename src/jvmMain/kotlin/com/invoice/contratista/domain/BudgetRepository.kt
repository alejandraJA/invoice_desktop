package com.invoice.contratista.domain

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.Budget
import com.invoice.contratista.data.source.web.models.Part

interface BudgetRepository {
    suspend fun getById(token: String, id: String, webStatus: WebStatus<Budget>)
    suspend fun createPart(token: String, idBudget: String, webStatus: WebStatus<List<Part>>)
}