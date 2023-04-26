package com.invoice.contratista.data.source.web.models.response.product

import com.google.gson.annotations.SerializedName
import com.invoice.contratista.data.source.web.models.response.DateModel

data class ProductModel(
        @SerializedName("id") val id: String,
        @SerializedName("modified") val modifiedModel: DateModel,
        @SerializedName("name") val name: String,
        @SerializedName("priceEntities") val priceEntities: List<PriceModel>,
        @SerializedName("productBase") val productBaseModel: ProductBaseModel,
        @SerializedName("taxEntities") val taxEntities: List<TaxModel>
)