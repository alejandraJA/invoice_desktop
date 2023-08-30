package com.invoice.contratista.ui.section.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import com.invoice.contratista.data.source.web.models.ProductInventory
import com.invoice.contratista.ui.custom.component.TextWithTitle
import com.invoice.contratista.ui.theme.ModifierCard
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.utils.*
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun InventoryLazy(onCloseRequest: (ProductInventory) -> Unit) = Window(
    onCloseRequest = {},
    title = SELECT_PRODUCT,
    state = WindowState(placement = WindowPlacement.Maximized),
    undecorated = true,
    transparent = true,
) {
    val productViewModel = remember { ProductViewModel() }
    val scope = rememberCoroutineScope()
    scope.launch {
        productViewModel.getAll()
    }
    Card(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        LazyVerticalGrid(
            modifier = ModifierCard,
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(productViewModel.inventory.value.size) { position ->
                val inventory = productViewModel.inventory.value[position]
                InventoryItem(inventory, onCloseRequest)
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun InventoryItem(inventory: ProductInventory, onClick: (ProductInventory) -> Unit) =
    ElevatedCard(onClick = {
        onClick.invoke(inventory)
    }) {
        val viewModel = remember { ProductViewModel() }
        val scope = rememberCoroutineScope()
        scope.launch {
            viewModel.getAvailability(inventory.id)
        }
        Row(
            modifier = ModifierCard,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextWithTitle(
                title = PRODUCT_SERVICE,
                text = inventory.product.name,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource("drawables/${
                    if (inventory.product.productBase.type) "inventory" 
                    else "engineering"
                }.svg"),
                contentDescription = TYPE,
                modifier = ModifierFieldImages
            )
        }
        Row(
            modifier = ModifierCard,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextWithTitle(
                title = SKU,
                text = inventory.product.productBase.sku + inventory.product.id,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = PRICE,
                modifier = Modifier.weight(1f),
                doubleMutableState = mutableStateOf(inventory.product.priceEntity.unitPrice),
                format = Constants.Format.Money
            )
        }
        if (viewModel.availability.value != null) Row(
            modifier = ModifierCard,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextWithTitle(
                title = QUANTITY,
                text = viewModel.availability.value!!.quantity.toString(),
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = RESERVED,
                text = viewModel.availability.value!!.reserved.toString(),
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = AVAILABLE,
                text = viewModel.availability.value!!.available.toString(),
                modifier = Modifier.weight(1f),
            )
        }
    }

