package com.invoice.contratista.utils

import com.invoice.contratista.data.source.web.models.response.event.Address
import com.invoice.contratista.data.source.web.models.response.event.AddressEntity


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

fun Address.getAddress() = "$street, " +
        "${
            if (interior.isEmpty())
                "$EXTERIOR $exterior"
            else "$INTERIOR $interior, " +
                    "$EXTERIOR $exterior"
        }, " +
        "$zip, $neighborhood, " +
        "$city, ${municipality}, " +
        "$state, $country"
