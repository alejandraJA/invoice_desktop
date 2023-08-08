package com.invoice.contratista.data.source.web.retrofit

import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.data.source.web.models.request.UpdateTokenRequest
import com.invoice.contratista.data.source.web.models.response.*
import com.invoice.contratista.data.source.web.models.response.customer.CustomerResponse
import com.invoice.contratista.data.source.web.models.response.event.EventModel
import com.invoice.contratista.data.source.web.models.response.event.Reserved
import com.invoice.contratista.utils.Constants.AUTHORIZATION
import retrofit2.Response
import retrofit2.http.*

interface Service {

    @GET("customer")
    suspend fun getCustomer(
        @Header(AUTHORIZATION) token: String
    ): Response<ResponseApi<List<CustomerResponse>>>

    @POST("sing/in")
    suspend fun singIn(@Body request: SingRequest): Response<ResponseApi<TokenModel?>>

    @POST("sing/up")
    suspend fun singUp(@Body request: SingRequest): Response<ResponseApi<UserModel>>

    @PUT("sing/updateToken")
    suspend fun updateToken(@Body request: UpdateTokenRequest): Response<ResponseApi<TokenModel>>

    @GET("event")
    suspend fun getAllEvents(@Header(AUTHORIZATION) token: String): Response<ResponseApi<List<EventModel>>>

    @GET("product/{id}")
    suspend fun getByIdProduct(
        @Header(AUTHORIZATION) token: String,
        @Path("id") idProduct: String
    ): Response<ResponseApi<ProductInventoryModel>>

    @PUT("reserved/{id}")
    fun updateProduct(
        @Header(AUTHORIZATION) token: String,
        @Path("id") idReserved: String,
        @Query("idProduct") idProduct: String,
    ): Response<ResponseApi<Reserved>>

    @GET("product/{id}/availability")
    fun getAvailability(
        @Header(AUTHORIZATION) token: String,
        @Path("id") idProduct: String
    ): Response<ResponseApi<Availability>>

    @GET("product/available")
    fun getAvailable(@Header(AUTHORIZATION) token: String): Response<ResponseApi<List<Available>>>

    @GET("product")
    fun getAll(@Header(AUTHORIZATION) token: String): Response<ResponseApi<List<ProductInventoryModel>>>

}