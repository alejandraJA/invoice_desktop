package com.invoice.contratista.data.source.web.models.response.product

import com.google.gson.annotations.SerializedName
import com.invoice.contratista.data.source.web.models.response.DateModel

data class CostModel(
        @SerializedName("date") val date: DateModel,
        @SerializedName("id") val id: String,
        @SerializedName("quantity") val quantity: Int,
        @SerializedName("unitCost") val unitCost: Double,
        @SerializedName("vendor") val vendorModel: VendorModel
)