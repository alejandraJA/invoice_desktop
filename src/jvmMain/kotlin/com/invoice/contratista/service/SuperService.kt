package com.invoice.contratista.service

import com.invoice.contratista.data.repository.web.utils.WebStatus

open class SuperService(private val userService: UserService) {
    val isUserLogged: Boolean
        get() = userService.isLoggedUser()
    var token: String?
        get() = userService.token
        set(value) {
            userService.token = value ?: ""
        }

    fun <T> getWebStatus(
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    ) = object : WebStatus<T> {
        override fun success(data: T) {
            if (data is List<*>) {
                if (data.isNotEmpty()) onSuccess.invoke(data)
                else onError.invoke("Empty list")
                return
            }
            onSuccess.invoke(data)
        }

        override fun error(e: String) {
            onError.invoke(e)
        }
    }
}