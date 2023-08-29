package com.invoice.contratista.ui.section.part

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.Part
import com.invoice.contratista.ui.custom.component.MoneyText
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.utils.PART
import com.invoice.contratista.utils.PRODUCT_SERVICE
import com.invoice.contratista.utils.TOTAL
import com.invoice.contratista.utils.UNIT_NAME

@ExperimentalMaterial3Api
@Composable
fun PartLazy(
    partEntities: List<Part>,
    partSelected: (Part) -> Unit
) = ElevatedCard {
    val typography = MaterialTheme.typography.bodySmall
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = ModifierFill,
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = PART,
                    modifier = Modifier.weight(1f).padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                    style = typography
                )
                Text(
                    text = PRODUCT_SERVICE,
                    modifier = Modifier.weight(2f).padding(top = 4.dp, bottom = 4.dp),
                    style = typography
                )
                Text(
                    text = "#",
                    modifier = Modifier.weight(1f).padding(top = 4.dp, bottom = 4.dp),
                    style = typography
                )
                Text(
                    text = UNIT_NAME,
                    modifier = Modifier.weight(1f).padding(top = 4.dp, bottom = 4.dp),
                    style = typography
                )
                Text(
                    text = TOTAL,
                    modifier = Modifier.weight(2f).padding(end = 8.dp, top = 4.dp, bottom = 4.dp),
                    style = typography
                )
            }
        }
        items(partEntities.size) {
            val part = partEntities[it]
            val viewModel = PartViewModel()
            viewModel.calculate(mutableStateOf(part))
            Row(
                modifier = Modifier.clickable { partSelected.invoke(part) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val modifier = Modifier.weight(1f)
                Text(
                    text = "$PART ${part.number}",
                    modifier = modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                    style = typography
                )
                Text(
                    text = part.reserved.inventory.product.productBase.description,
                    modifier = Modifier.weight(2f).padding(top = 4.dp, bottom = 4.dp),
                    style = typography
                )
                Text(
                    text = part.quantity.toString(),
                    modifier = modifier.padding(top = 4.dp, bottom = 4.dp),
                    style = typography
                )
                Text(
                    text = part.reserved.inventory.product.productBase.unitName,
                    modifier = modifier.padding(top = 4.dp, bottom = 4.dp),
                    style = typography
                )
                MoneyText(
                    money = viewModel.total.value,
                    modifierRow = Modifier.weight(2f).padding(end = 8.dp, top = 4.dp, bottom = 4.dp),
                )
            }
        }
    }
}
