package com.invoice.contratista.data.source.web.retrofit

import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.data.source.web.models.request.UpdateTokenRequest
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.ResponseApi
import com.invoice.contratista.data.source.web.models.response.TokenModel
import com.invoice.contratista.data.source.web.models.response.UserModel
import com.invoice.contratista.data.source.web.models.response.customer.CustomerResponse
import com.invoice.contratista.data.source.web.models.response.event.EventModel
import com.invoice.contratista.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface Service {

    @GET("customer")
    suspend fun getCustomer(
        @Header(Constants.AUTHORIZATION) token: String
    ): Response<ResponseApi<List<CustomerResponse>>>

    @POST("sing/in")
    suspend fun singIn(@Body request: SingRequest): Response<ResponseApi<TokenModel?>>

    @POST("sing/up")
    suspend fun singUp(@Body request: SingRequest): Response<ResponseApi<UserModel>>

    @PUT("sing/updateToken")
    suspend fun updateToken(@Body request: UpdateTokenRequest): Response<ResponseApi<TokenModel>>

    @GET("event")
    suspend fun getAllEvents(@Header(Constants.AUTHORIZATION) token: String): Response<ResponseApi<List<EventModel>>>

    @GET("product/{id}")
    suspend fun getByIdProduct(
        @Header(Constants.AUTHORIZATION) token: String,
        @Path("id") idProduct: String
    ): Response<ResponseApi<ProductInventoryModel>>

}