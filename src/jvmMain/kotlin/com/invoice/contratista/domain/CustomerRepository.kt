package com.invoice.contratista.domain

import com.invoice.contratista.data.source.web.models.Customer
import com.invoice.contratista.data.source.web.models.ResponseApi
import retrofit2.Response

interface CustomerRepository {
    suspend fun getCustomer(token: String): Response<ResponseApi<List<Customer>>>
}