package com.invoice.contratista.ui.section.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import kotlinx.coroutines.launch

@Composable
fun InventoryLazy() = Column {
    val productViewModel = ProductViewModel()
    val scope = rememberCoroutineScope()
    scope.launch {
        productViewModel.getAll()
    }
    LazyColumn {
        items(productViewModel.inventory.value.size) { position ->
            val inventory = productViewModel.inventory.value[position]
            InventoryItem(inventory)
        }
    }
}

@Composable
fun InventoryItem(inventory: ProductInventoryModel) = ElevatedCard {
    val viewModel = ProductViewModel()
    val scope = rememberCoroutineScope()
    scope.launch {
        viewModel.getAvailability(inventory.id)
    }

}

