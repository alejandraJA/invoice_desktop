package com.invoice.contratista.data.source.web.models

data class UpdateTokenRequest(
    val username: String,
    val token: String,
)