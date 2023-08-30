package com.invoice.contratista.service

import com.invoice.contratista.data.source.web.models.Part
import com.invoice.contratista.data.source.web.models.Reserved
import com.invoice.contratista.domain.ReservedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReservedService(
    private val repository: ReservedRepository,
    userService: UserService
) : SuperService(userService) {

    suspend fun updateProduct(
        idReserved: String,
        idProduct: String,
        onSuccess: (Reserved) -> Unit,
        onError: (String) -> Unit,
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.updateProduct(token!!, idReserved, idProduct, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun deletePart(
        id: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.deletePart(token!!, id, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun updatePart(
        idReserved: String,
        quantity: Int,
        discount: Double,
        onSuccess: (Part) -> Unit,
        onError: (String) -> Unit,
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) repository.updatePart(
            token!!,
            idReserved,
            quantity,
            discount,
            getWebStatus(onSuccess, onError)
        )
    }

}