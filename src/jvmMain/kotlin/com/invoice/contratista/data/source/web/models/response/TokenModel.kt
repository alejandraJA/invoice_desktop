package com.invoice.contratista.data.source.web.models.response

import java.util.*

data class TokenModel(
    val token: String,
    val expiration: Date,
)