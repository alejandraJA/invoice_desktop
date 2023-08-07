package com.invoice.contratista.ui.section.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.ui.custom.component.items.TextWithTitle
import com.invoice.contratista.utils.AVAILABLE
import com.invoice.contratista.utils.COST_LIST
import com.invoice.contratista.utils.INVENTORY
import com.invoice.contratista.utils.MODIFIED

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun ProductInventorySection(inventory: MutableState<ProductInventoryModel?>) = Column {
    val alpha = Modifier.alpha(0.5f).padding(top = 4.dp)
    val typography = MaterialTheme.typography.bodySmall

    if (inventory.value != null) Column(modifier = Modifier.padding(top = 4.dp)) {
        Text(text = INVENTORY, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 4.dp))
        Row {
            TextWithTitle(
                title = AVAILABLE,
                text = inventory.value!!.quantity.toString(),
                modifier = Modifier.weight(1f),
            )
            TextWithTitle(
                title = MODIFIED,
                text = inventory.value!!.modified!!,
                modifier = Modifier.weight(1f),
            )
        }
        Text(text = COST_LIST, modifier = alpha.padding(top = 4.dp), style = typography)
        CostLazy(inventory.value!!.costEntities)
    }

}