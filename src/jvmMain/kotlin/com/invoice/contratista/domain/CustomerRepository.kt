package com.invoice.contratista.domain

import com.invoice.contratista.data.source.web.models.response.ResponseApi
import com.invoice.contratista.data.source.web.models.response.customer.CustomerResponse
import retrofit2.Response

interface CustomerRepository {
    suspend fun getCustomer(token: String): Response<ResponseApi<List<CustomerResponse>>>
}