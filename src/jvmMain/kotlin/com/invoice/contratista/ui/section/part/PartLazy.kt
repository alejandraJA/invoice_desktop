package com.invoice.contratista.ui.section.part

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.ui.theme.ModifierFill
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
        ElevatedCard(onClick = { partSelected.invoke(part) }) {
            val modifier = Modifier.weight(1f)
            val typography = MaterialTheme.typography.bodySmall
            Row(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "$PART ${part.number}",
                    modifier = modifier, style = typography
                )
                Text(
                    text = part.reserved.product.productBase.description,
                    modifier = Modifier.weight(2f), style = typography
                )
                Text(
                    text = part.quantity.toString(),
                    modifier = modifier, style = typography
                )
                Text(
                    text = part.reserved.product.productBase.unitName,
                    modifier = modifier, style = typography
                )
                Text(text = "$ 200.00", modifier = Modifier.weight(2f), style = typography)
            }
        }
    }
}