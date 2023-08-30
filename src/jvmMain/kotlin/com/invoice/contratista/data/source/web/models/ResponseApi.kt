package com.invoice.contratista.data.source.web.models

data class ResponseApi<T>(
    val status: Boolean,
    val message: String,
    val data: T?,
)
