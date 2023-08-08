package com.invoice.contratista.service

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.event.Reserved
import com.invoice.contratista.domain.ReservedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReservedService(
    private val repository: ReservedRepository,
    private val userService: UserService
) {
    private val isUserLogged: Boolean
        get() = userService.isLoggedUser()
    private val token: String?
        get() = userService.getToken()

    suspend fun updateProduct(
        idReserved: String,
        idProduct: String,
        onSuccess: (Reserved) -> Unit,
        onError: (String) -> Unit,
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged && token!!.isNotEmpty()) {
            repository.updateProduct(token!!, idReserved, idProduct, getWebStatus(onSuccess, onError))
        }
    }

    private fun getWebStatus(
        onSuccess: (Reserved) -> Unit,
        onError: (String) -> Unit
    ) = object : WebStatus<Reserved> {
        override fun success(data: Reserved) {
            onSuccess.invoke(data)
        }

        override fun error(e: String) {
            onError.invoke(e)
        }

    }
}