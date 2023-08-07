package com.invoice.contratista.data.repository.web

import com.invoice.contratista.data.repository.web.utils.Resolve
import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.event.Reserved
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.domain.ReservedRepository

class ReservedRepositoryImp(private val service: Service) : ReservedRepository {
    override suspend fun updateProduct(token: String, idReserved: String, idProduct: String, webStatus: WebStatus<Reserved>): Unit =
        Resolve(service.updateProduct(token, idReserved, idProduct), webStatus).invoke()
}