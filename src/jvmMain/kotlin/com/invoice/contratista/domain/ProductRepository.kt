package com.invoice.contratista.domain

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.Availability
import com.invoice.contratista.data.source.web.models.response.Available
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel

interface ProductRepository {
    suspend fun getByIdProduct(
        token: String,
        idProduct: String,
        webStatus: WebStatus<ProductInventoryModel>
    )

    suspend fun getAvailability(
        token: String,
        idProduct: String, webStatus: WebStatus<Availability>
    )

    suspend fun getAvailable(token: String, webStatus: WebStatus<List<Available>>)

    suspend fun getAll(token: String, webStatus: WebStatus<List<ProductInventoryModel>>)
}