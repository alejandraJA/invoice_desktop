package com.invoice.contratista.service

import com.invoice.contratista.data.repository.web.utils.WebStatus
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

    private fun getWebStatus(
        onSuccess: (ProductInventoryModel) -> Unit,
        onError: (String) -> Unit
    ) = object : WebStatus<ProductInventoryModel> {
        override fun success(data: ProductInventoryModel) {
            onSuccess.invoke(data)
        }

        override fun error(e: String) {
            onError.invoke(e)
        }
    }
}