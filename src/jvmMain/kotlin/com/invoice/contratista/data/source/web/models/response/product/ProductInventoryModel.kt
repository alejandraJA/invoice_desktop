package com.invoice.contratista.data.source.web.models.response.product

import com.google.gson.annotations.SerializedName
import com.invoice.contratista.data.source.web.models.response.DateModel

data class ProductInventoryModel(
        @SerializedName("id") val id: String,
        @SerializedName("quantity") val quantity: Int,
        @SerializedName("modified") val modified: DateModel,
        @SerializedName("product") val product: ProductModel,
        @SerializedName("costEntities") val cost: List<CostModel>,
)