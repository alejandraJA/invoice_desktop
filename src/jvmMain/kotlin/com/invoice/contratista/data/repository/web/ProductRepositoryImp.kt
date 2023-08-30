package com.invoice.contratista.data.repository.web

import com.invoice.contratista.data.repository.web.utils.Resolve
import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.Availability
import com.invoice.contratista.data.source.web.models.Available
import com.invoice.contratista.data.source.web.models.ProductInventory
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.domain.ProductRepository

class ProductRepositoryImp(private val service: Service) : ProductRepository {
    override suspend fun getByIdProduct(
        token: String,
        idProduct: String,
        webStatus: WebStatus<ProductInventory>
    ): Unit = Resolve(service.getByIdProduct(token, idProduct), webStatus).invoke()

    override suspend fun getAvailability(token: String, idProduct: String, webStatus: WebStatus<Availability>) =
        Resolve(service.getAvailability(token, idProduct), webStatus).invoke()

    override suspend fun getAvailable(token: String, webStatus: WebStatus<List<Available>>) =
        Resolve(service.getAvailable(token), webStatus).invoke()

    override suspend fun getAll(token: String, webStatus: WebStatus<List<ProductInventory>>) =
        Resolve(service.getAllProducts(token), webStatus).invoke()
}