package com.invoice.contratista.data.repository.web


import com.invoice.contratista.data.source.web.models.Customer
import com.invoice.contratista.data.source.web.models.ResponseApi
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.domain.CustomerRepository
import retrofit2.Response

class CustomerRepositoryImp(private val service: Service) : CustomerRepository {
    override suspend fun getCustomer(token: String): Response<ResponseApi<List<Customer>>> =
            service.getCustomer(token)
}