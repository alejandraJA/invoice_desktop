package com.invoice.contratista.data.source.web.models.response.product

import com.google.gson.annotations.SerializedName

data class TaxModel(
    @SerializedName("factor") val factor: String,
    @SerializedName("id") val id: String,
    @SerializedName("localTax") val localTax: Boolean,
    @SerializedName("rate") val rate: Double,
    @SerializedName("type") val type: String,
    @SerializedName("withholding") val withholding: Boolean
)