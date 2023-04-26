package com.invoice.contratista.data.source.web.models.response.product

import com.google.gson.annotations.SerializedName

data class ProductBaseModel(
    @SerializedName("id") val id: String,
    @SerializedName("description") val description: String,
    @SerializedName("sku") val sku: String,
    @SerializedName("taxIncluded") val taxIncluded: Boolean,
    @SerializedName("taxability") val taxability: String,
    @SerializedName("unitKey") val unitKey: String,
    @SerializedName("unitName") val unitName: String,
    @SerializedName("productKey") val productKey: String,
)