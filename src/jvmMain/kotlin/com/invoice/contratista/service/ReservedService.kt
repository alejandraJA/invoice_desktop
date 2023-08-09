package com.invoice.contratista.service

import com.invoice.contratista.data.source.web.models.response.event.Reserved
import com.invoice.contratista.domain.ReservedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReservedService(
    private val repository: ReservedRepository,
    userService: UserService
): SuperService(userService) {

    suspend fun updateProduct(
        idReserved: String,
        idProduct: String,
        onSuccess: (Reserved) -> Unit,
        onError: (String) -> Unit,
    ) = withContext(Dispatchers.IO) {
        if (condition) {
            repository.updateProduct(token!!, idReserved, idProduct, getWebStatus(onSuccess, onError))
        }
    }

}