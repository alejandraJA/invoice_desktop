package com.invoice.contratista.domain

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.event.Reserved

interface ReservedRepository {
    suspend fun updateProduct(token: String, idReserved: String, idProduct: String, webStatus: WebStatus<Reserved>)
}