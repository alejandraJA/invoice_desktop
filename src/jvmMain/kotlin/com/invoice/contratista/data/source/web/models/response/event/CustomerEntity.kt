package com.invoice.contratista.data.source.web.models.response.event

data class CustomerEntity(
    val addressEntity: AddressEntity,
    val createdAt: String,
    val email: String,
    val id: String,
    val legalName: String,
    val liveMode: Boolean,
    val organization: String,
    val phone: String,
    val taxId: String,
    val taxSystem: String
)