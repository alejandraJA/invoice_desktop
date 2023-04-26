package com.invoice.contratista.sys.component

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.invoice.contratista.sys.service.UserService

class LocalComponent : KoinComponent {
    private val userService: UserService by inject()

}