package com.invoice.contratista.domain

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.event.BudgetEntity

interface BudgetRepository {
    suspend fun getById(token: String, id: String, webStatus: WebStatus<BudgetEntity>)
}