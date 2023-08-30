package com.invoice.contratista.data.source.web.models

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id") val id: Int,
        @SerializedName("registration") val registration: String,
        @SerializedName("username") val username: String
)