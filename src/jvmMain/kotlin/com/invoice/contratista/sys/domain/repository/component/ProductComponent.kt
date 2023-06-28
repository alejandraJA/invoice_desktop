package com.invoice.contratista.sys.domain.repository.component

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.sys.domain.repository.web.ProductRepository
import com.invoice.contratista.sys.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductComponent : KoinComponent {
    private val repository: ProductRepository by inject()
    private val userService: UserService by inject()

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
            if (data != null) onSuccess.invoke(data)
            else onError.invoke("Empty list")
        }

        override fun error(e: String) {
            onError.invoke(e)
        }
    }
}