package com.invoice.contratista.sys.domain.repository.web

import com.invoice.contratista.data.source.web.models.response.ResponseApi
import com.invoice.contratista.data.source.web.models.response.product.ProductInventoryModel
import retrofit2.Response

interface ProductRepository {
    suspend fun getProducts(token: String): Response<ResponseApi<List<ProductInventoryModel>>>
}