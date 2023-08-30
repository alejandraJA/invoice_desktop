package com.invoice.contratista.ui.section.part

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.Part
import com.invoice.contratista.ui.section.product.ProductSection


@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun PartSection(
    part: MutableState<Part?>,
    modifier: Modifier,
    onChangePart: () -> Unit
) = Row(modifier = modifier.padding(start = 8.dp)) {
    val viewModel = remember { PartViewModel() }
    viewModel.setPart(part)
    viewModel.setOnUpdateReserved(onChangePart)
    PartContent(viewModel = viewModel, modifier = Modifier.weight(1f), onChangePart)
    ElevatedCard(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
        if (part.value != null) ProductSection(part.value!!.reserved.inventory)
    }
}