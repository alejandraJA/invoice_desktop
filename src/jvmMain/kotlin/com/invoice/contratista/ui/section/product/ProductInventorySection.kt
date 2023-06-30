package com.invoice.contratista.ui.section.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.sys.domain.repository.component.ProductComponent
import com.invoice.contratista.ui.custom.component.ErrorDialog
import com.invoice.contratista.ui.custom.component.LoadingDialog
import com.invoice.contratista.utils.AVAILABLE
import com.invoice.contratista.utils.COST_LIST
import com.invoice.contratista.utils.INVENTORY
import com.invoice.contratista.utils.MODIFIED
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun ProductInventorySection(idProduct: String) = Column {
    val scope = rememberCoroutineScope()
    val loadingDialogState = rememberSaveable { mutableStateOf(true) }
    val errorState = rememberSaveable { mutableStateOf("") }
    val inventory = remember { mutableStateOf<ProductInventoryModel?>(null) }
    val alpha = Modifier.alpha(0.5f).padding(top = 4.dp)
    val typography = MaterialTheme.typography.bodySmall

    scope.launch {
        val component = ProductComponent()
        component.findByProductId(idProduct, {
            loadingDialogState.value = false
            inventory.value = it
        }, { errorState.value = it })
    }

    if (inventory.value != null) Column(modifier = Modifier.padding(top = 4.dp)) {
        Text(text = INVENTORY, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 4.dp))
        Row {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = AVAILABLE, modifier = alpha, style = typography)
                Text(text = inventory.value!!.quantity.toString(), style = typography)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(text = MODIFIED, modifier = alpha, style = typography)
                Text(text = inventory.value!!.modified!!.timestamp.substring(0..9), style = typography)
            }
        }
        Text(text = COST_LIST, modifier = alpha.padding(top = 4.dp), style = typography)
        CostLazy(inventory.value!!.costEntities)
    }

    LoadingDialog(show = loadingDialogState) { loadingDialogState.value = false }
    ErrorDialog(errorState) {
        errorState.value = ""
    }
}