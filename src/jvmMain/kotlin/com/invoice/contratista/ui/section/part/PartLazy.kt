package com.invoice.contratista.ui.section.part

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.Part
import com.invoice.contratista.ui.custom.component.MoneyText
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.utils.*

@ExperimentalMaterial3Api
@Composable
fun PartLazy(
    partEntities: List<Part>,
    partSelected: (Part) -> Unit,
    addPart: () -> Unit,
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
                Row(modifier = Modifier.weight(1f)) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = PART,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                        style = typography
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Row(modifier = Modifier.weight(2f)) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = PRODUCT_SERVICE,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                        style = typography
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Row(modifier = Modifier.weight(1f)) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "#",
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                        style = typography
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Row(modifier = Modifier.weight(1f)) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = UNIT_NAME,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                        style = typography
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Row(modifier = Modifier.weight(2f)) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = TOTAL,
                        modifier = Modifier.padding(end = 8.dp, top = 4.dp, bottom = 4.dp),
                        style = typography
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
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

        item {
            Row(
                modifier = Modifier.fillMaxWidth().clickable { addPart.invoke() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = ADD_PART, modifier = Modifier.padding(vertical = 4.dp))
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}