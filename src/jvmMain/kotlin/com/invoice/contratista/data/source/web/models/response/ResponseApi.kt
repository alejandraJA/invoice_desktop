package com.invoice.contratista.data.source.web.models.response

data class ResponseApi<T>(
    val status: Boolean,
    val message: String,
    val data: T?,
)
