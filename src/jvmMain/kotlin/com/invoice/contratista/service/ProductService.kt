package com.invoice.contratista.service

import com.invoice.contratista.data.source.web.models.response.Availability
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.domain.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductService(
    private val repository: ProductRepository,
    userService: UserService
): SuperService(userService) {

    suspend fun findByProductId(
        productId: String,
        onSuccess: (ProductInventoryModel) -> Unit,
        onError: (String) -> Unit,
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.getByIdProduct(token!!, productId, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun getAll(
        onSuccess: (List<ProductInventoryModel>) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.getAll(token!!, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun getAvailability(
        idProduct: String,
        onSuccess: (Availability) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.getAvailability(token!!, idProduct, getWebStatus(onSuccess, onError))
        }
    }


}