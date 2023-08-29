package com.invoice.contratista.domain

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.event.Part
import com.invoice.contratista.data.source.web.models.response.event.Reserved

interface ReservedRepository {
    suspend fun updateProduct(token: String, idReserved: String, idProduct: String, webStatus: WebStatus<Reserved>)
    suspend fun deletePart(token: String, idReserved: String, webStatus: WebStatus<Boolean>)

    suspend fun updatePart(token: String, idReserved: String, quantity: Int, discount: Double, webStatus: WebStatus<Part>)
}