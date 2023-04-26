package com.invoice.contratista.data.source.web.models.response

import com.google.gson.annotations.SerializedName
import com.invoice.contratista.data.source.web.models.response.DateModel

data class UserModel(
        @SerializedName("id") val id: Int,
        @SerializedName("registration") val registration: DateModel,
        @SerializedName("username") val username: String
)