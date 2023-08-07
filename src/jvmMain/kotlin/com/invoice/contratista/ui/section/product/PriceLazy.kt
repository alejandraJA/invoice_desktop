package com.invoice.contratista.ui.section.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.PriceEntity
import com.invoice.contratista.ui.theme.ModifierCard

@ExperimentalMaterial3Api
@Composable
fun PriceLazy(priceEntities: List<PriceEntity>) = LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    verticalArrangement = Arrangement.spacedBy(4.dp),
    horizontalArrangement = Arrangement.spacedBy(4.dp),
) {
    items(priceEntities.size) {
        val price = priceEntities[it]
        ElevatedCard(onClick = {}) {
            Column (modifier = ModifierCard) {
                Text(
                    text = "$ ${price.unitPrice}",
                    modifier = Modifier.padding(end = 8.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = price.date,
                    modifier = Modifier.alpha(0.5f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}