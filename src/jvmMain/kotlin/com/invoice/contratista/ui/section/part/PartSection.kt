package com.invoice.contratista.ui.section.part

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.ui.section.product.ProductSection

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun PartSection(part: PartEntity?, modifier: Modifier) = Row(modifier = modifier.padding(start = 8.dp)) {
    ElevatedCard {
        ProductSection(part!!.reserved.product)
    }
}