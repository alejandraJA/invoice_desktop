package com.invoice.contratista.data.source.web.models

data class Address(
    val city: String,
    val country: String,
    val exterior: String,
    val id: String,
    val interior: String,
    val municipality: String,
    val neighborhood: String,
    val state: String,
    val street: String,
    val zip: String
)