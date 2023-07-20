package com.invoice.contratista.ui.section.part

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.ui.section.product.ProductSection


@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun PartSection(
    part: MutableState<PartEntity?>,
    inventory: MutableState<ProductInventoryModel?>,
    modifier: Modifier
) = Row(modifier = modifier.padding(start = 8.dp)) {
    PartContent(inventory, part, modifier = Modifier.weight(1f))
    ElevatedCard(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
        ProductSection(part.value!!.reserved.product, inventory)
    }
}