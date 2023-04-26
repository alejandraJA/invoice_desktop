package com.invoice.contratista.data.repository.web


import com.invoice.contratista.data.source.web.models.response.ResponseApi
import com.invoice.contratista.data.source.web.models.response.customer.CustomerResponse
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.sys.domain.repository.web.CustomerRepository
import retrofit2.Response

class CustomerRepositoryImp(private val service: Service) : CustomerRepository {
    override suspend fun getCustomer(token: String): Response<ResponseApi<List<CustomerResponse>>> =
            service.getCustomer(token)
}