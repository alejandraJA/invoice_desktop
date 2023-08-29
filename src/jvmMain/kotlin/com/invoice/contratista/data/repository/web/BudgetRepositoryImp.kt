package com.invoice.contratista.data.repository.web

import com.invoice.contratista.data.repository.web.utils.Resolve
import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.event.BudgetEntity
import com.invoice.contratista.data.source.web.models.response.event.Part
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.domain.BudgetRepository

class BudgetRepositoryImp(private val service: Service) : BudgetRepository {
    override suspend fun getById(token: String, id: String, webStatus: WebStatus<BudgetEntity>) =
        Resolve(service.getBudgetById(token, id), webStatus).invoke()

    override suspend fun createPart(token: String, idBudget: String, webStatus: WebStatus<List<Part>>) =
        Resolve(service.createPart(token, idBudget), webStatus).invoke()
}