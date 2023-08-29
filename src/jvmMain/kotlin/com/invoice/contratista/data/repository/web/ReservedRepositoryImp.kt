package com.invoice.contratista.data.repository.web

import com.invoice.contratista.data.repository.web.utils.Resolve
import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.event.Part
import com.invoice.contratista.data.source.web.models.response.event.Reserved
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.domain.ReservedRepository

class ReservedRepositoryImp(private val service: Service) : ReservedRepository {
    override suspend fun updateProduct(
        token: String,
        idReserved: String,
        idProduct: String,
        webStatus: WebStatus<Reserved>
    ): Unit = Resolve(service.updateProductInPart(token, idReserved, idProduct), webStatus).invoke()

    override suspend fun deletePart(
        token: String,
        idReserved: String,
        webStatus: WebStatus<Boolean>
    ): Unit = Resolve(service.deletePart(token, idReserved), webStatus).invoke()

    override suspend fun updatePart(
        token: String,
        idReserved: String,
        quantity: Int,
        discount: Double,
        webStatus: WebStatus<Part>
    ): Unit = Resolve(service.updatePart(token, idReserved, quantity, discount), webStatus).invoke()


}