package com.invoice.contratista.domain

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.Availability
import com.invoice.contratista.data.source.web.models.Available
import com.invoice.contratista.data.source.web.models.ProductInventory

interface ProductRepository {
    suspend fun getByIdProduct(
        token: String,
        idProduct: String,
        webStatus: WebStatus<ProductInventory>
    )

    suspend fun getAvailability(
        token: String,
        idProduct: String, webStatus: WebStatus<Availability>
    )

    suspend fun getAvailable(token: String, webStatus: WebStatus<List<Available>>)

    suspend fun getAll(token: String, webStatus: WebStatus<List<ProductInventory>>)
}