package com.invoice.contratista.data.source.local

data class UserLogged(
        val email: String,
        var token: String,
        val password: String,
)
