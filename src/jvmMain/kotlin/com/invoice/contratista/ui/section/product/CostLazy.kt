package com.invoice.contratista.ui.section.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.Cost
import com.invoice.contratista.ui.theme.ModifierCard

@ExperimentalMaterial3Api
@Composable
fun CostLazy(costEntities: List<Cost>) = LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    verticalArrangement = Arrangement.spacedBy(4.dp),
    horizontalArrangement = Arrangement.spacedBy(4.dp),
) {
    items(costEntities.size) {
        val costEntity = costEntities[it]
        ElevatedCard {
            Column (modifier = ModifierCard) {
                Text(
                    text = "$ ${costEntity.unitCost}",
                    modifier = Modifier.padding(end = 8.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = costEntity.date,
                    modifier = Modifier.alpha(0.5f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}