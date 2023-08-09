package com.invoice.contratista.ui.section.product

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.response.Availability
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.service.ProductService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductViewModel : KoinComponent {
    private val service: ProductService by inject()

    private val loading: MutableState<Boolean> = mutableStateOf(true)
    private val error: MutableState<String> = mutableStateOf("")

    val inventory = mutableStateOf<List<ProductInventoryModel>>(listOf())
    val availability = mutableStateOf<Availability?>(null)

    private val onError = { e: String ->
        error.value = e
        loading.value = false
    }

    suspend fun getAvailability(idProduct: String) {
        if (availability.value == null) service.getAvailability(
            idProduct = idProduct,
            onSuccess = { availability ->
                this.availability.value = availability
                loading.value = false
            },
            onError = onError
        )
    }

    suspend fun getAll() {
        if (inventory.value.isEmpty()) service.getAll(onSuccess = {
            inventory.value = it
            loading.value = false
        }, onError = onError)
    }

}