package com.invoice.contratista.utils

import com.invoice.contratista.data.source.web.models.response.event.AddressEntity

object AddressUtils {
    fun AddressEntity.getAddress() = "$street, " +
            "${
                if (interior.isEmpty())
                    "$EXTERIOR $exterior"
                else "$INTERIOR $interior, " +
                        "$EXTERIOR $exterior"
            }, " +
            "$zip, $neighborhood, " +
            "$city, ${municipality}, " +
            "$state, $country"
}