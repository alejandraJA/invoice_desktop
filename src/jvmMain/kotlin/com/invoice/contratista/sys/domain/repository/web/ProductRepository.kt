package com.invoice.contratista.sys.domain.repository.web

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel

fun interface ProductRepository {
    suspend fun getByIdProduct(
        token: String,
        idProduct: String,
        webStatus: WebStatus<ProductInventoryModel>
    )
}