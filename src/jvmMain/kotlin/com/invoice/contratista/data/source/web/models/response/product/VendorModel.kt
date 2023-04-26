package com.invoice.contratista.data.source.web.models.response.product

import com.google.gson.annotations.SerializedName
import com.invoice.contratista.data.source.web.models.response.customer.Address

data class VendorModel(
        @SerializedName("address") val address: Address,
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String
)