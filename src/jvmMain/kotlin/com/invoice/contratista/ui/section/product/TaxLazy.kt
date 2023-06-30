package com.invoice.contratista.ui.section.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.TaxEntity
import com.invoice.contratista.utils.LOCAL_TAX

@Composable
fun TaxLazy(taxEntities: List<TaxEntity>) = LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    verticalArrangement = Arrangement.spacedBy(4.dp),
    horizontalArrangement = Arrangement.spacedBy(4.dp),
) {
    items(taxEntities.size) {
        ElevatedCard {
            val tax = taxEntities[it]
            Text(
                text = "${tax.type} ${tax.rate * 100}%",
                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "${tax.factor}${
                    if (tax.localTax) " $LOCAL_TAX" else ""
                }",
                modifier = Modifier.alpha(0.5f).padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}