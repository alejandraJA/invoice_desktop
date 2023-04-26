package com.invoice.contratista.data.repository.web

import com.invoice.contratista.data.source.web.models.response.ResponseApi
import com.invoice.contratista.data.source.web.models.response.product.ProductInventoryModel
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.sys.domain.repository.web.ProductRepository
import retrofit2.Response

class ProductRepositoryImp (private val service: Service) : ProductRepository {
    override suspend fun getProducts(token: String): Response<ResponseApi<List<ProductInventoryModel>>> =
        service.getProducts(token)
}