package com.invoice.contratista.data.repository.web.utils

interface WebStatus<T> {
    fun success(data: T)
    fun error(e: String)
}