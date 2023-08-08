package com.invoice.contratista.service

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.Availability
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.domain.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductService(
    private val repository: ProductRepository,
    private val userService: UserService
) {
    private val isUserLogged: Boolean
        get() = userService.isLoggedUser()
    private val token: String?
        get() = userService.getToken()

    suspend fun findByProductId(
        productId: String,
        onSuccess: (ProductInventoryModel) -> Unit,
        onError: (String) -> Unit,
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged && token!!.isNotEmpty()) {
            repository.getByIdProduct(token!!, productId, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun getAll(
        onSuccess: (List<ProductInventoryModel>) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged && token!!.isNotEmpty()) {
            repository.getAll(token!!, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun getAvailability(
        idProduct: String,
        onSuccess: (Availability) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged && token!!.isNotEmpty()) {
            repository.getAvailability(token!!, idProduct, getWebStatus(onSuccess, onError))
        }
    }

    private fun <T> getWebStatus(
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    ) = object : WebStatus<T> {
        override fun success(data: T) {
            onSuccess.invoke(data)
        }

        override fun error(e: String) {
            onError.invoke(e)
        }
    }

}