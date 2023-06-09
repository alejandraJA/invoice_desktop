package com.invoice.contratista.data.source.web.models.request

data class UpdateTokenRequest(
    val username: String,
    val token: String,
)