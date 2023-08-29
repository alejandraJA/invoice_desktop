package com.invoice.contratista.data.source.web.retrofit

import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.data.source.web.models.request.UpdateTokenRequest
import com.invoice.contratista.data.source.web.models.response.*
import com.invoice.contratista.data.source.web.models.response.customer.CustomerResponse
import com.invoice.contratista.data.source.web.models.response.event.BudgetEntity
import com.invoice.contratista.data.source.web.models.response.event.EventModel
import com.invoice.contratista.data.source.web.models.response.event.Part
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

    @GET("product/{id}/availability")
    suspend fun getAvailability(
        @Header(AUTHORIZATION) token: String,
        @Path("id") idProduct: String
    ): Response<ResponseApi<Availability>>

    @GET("product/available")
    suspend fun getAvailable(@Header(AUTHORIZATION) token: String): Response<ResponseApi<List<Available>>>

    @GET("product")
    suspend fun getAllProducts(@Header(AUTHORIZATION) token: String): Response<ResponseApi<List<ProductInventoryModel>>>

    @GET("budget/{id}")
    suspend fun getBudgetById(
        @Header(AUTHORIZATION) token: String,
        @Path("id") id: String
    ): Response<ResponseApi<BudgetEntity>>

    @POST("budget/{id}/createPart")
    suspend fun createPart(
        @Header(AUTHORIZATION) token: String,
        @Path("id") idBudget: String
    ): Response<ResponseApi<List<Part>>>

    @PUT("reserved/{id}")
    suspend fun updateProductInPart(
        @Header(AUTHORIZATION) token: String,
        @Path("id") idReserved: String,
        @Query("idProduct") idProduct: String,
    ): Response<ResponseApi<Reserved>>

    @DELETE(("reserved/{idReserved}"))
    suspend fun deletePart(
        @Header(AUTHORIZATION) token: String,
        @Path("idReserved") idReserved: String
    ): Response<ResponseApi<Boolean>>


    @PUT("part/{idReserved}")
    suspend fun updatePart(
        @Header(AUTHORIZATION) token: String,
        @Path("idReserved") idReserved: String,
        @Query("quantity") quantity:Int,
        @Query("discount") discount: Double,
    ): Response<ResponseApi<Part>>

}