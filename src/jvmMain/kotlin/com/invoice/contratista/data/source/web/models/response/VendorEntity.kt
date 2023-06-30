package com.invoice.contratista.data.source.web.models.response

import com.invoice.contratista.data.source.web.models.response.event.AddressEntity

data class VendorEntity(
    val id: String,
    val name: String,
    var address: AddressEntity? = null
)