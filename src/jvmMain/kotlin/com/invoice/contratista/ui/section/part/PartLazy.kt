package com.invoice.contratista.ui.section.part

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.utils.MoneyUtils.moneyFormat
import com.invoice.contratista.utils.PART

@ExperimentalMaterial3Api
@Composable
fun PartLazy(
    partEntities: List<PartEntity>,
    partSelected: (PartEntity) -> Unit
) = LazyColumn(
    verticalArrangement = Arrangement.spacedBy(4.dp),
    modifier = ModifierFill
) {
    items(partEntities.size) {
        val part = partEntities[it]
        val viewModel = PartViewModel()
        viewModel.calculate(mutableStateOf(part))
        ElevatedCard(onClick = { partSelected.invoke(part) }) {
            val modifier = Modifier.weight(1f)
            val typography = MaterialTheme.typography.bodySmall
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$PART ${part.number}",
                    modifier = modifier,
                    style = typography
                )
                Text(
                    text = part.reserved.product.productBase.description,
                    modifier = Modifier.weight(2f),
                    style = typography
                )
                Text(
                    text = part.quantity.toString(),
                    modifier = modifier,
                    style = typography
                )
                Text(
                    text = part.reserved.product.productBase.unitName,
                    modifier = modifier,
                    style = typography
                )
                Row(
                    modifier = Modifier.weight(2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Icon(
                        painter = painterResource("drawables/money.svg"),
                        contentDescription = "money",
                        modifier = ModifierFieldImages
                    )
                    Text(text = viewModel.total.value.moneyFormat(), style = typography)
                }
            }
        }
    }
}