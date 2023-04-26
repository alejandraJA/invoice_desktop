package com.invoice.contratista.data.source.web.models.response.customer


import com.google.gson.annotations.SerializedName
import java.util.*

data class Address(
    @SerializedName("id")
    val id: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String,
    @SerializedName("exterior")
    val exterior: String?,
    @SerializedName("interior")
    val interior: String?,
    @SerializedName("municipality")
    val municipality: String,
    @SerializedName("neighborhood")
    val neighborhood: String?,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    val street: String?,
    @SerializedName("zip")
    val zip: String
)