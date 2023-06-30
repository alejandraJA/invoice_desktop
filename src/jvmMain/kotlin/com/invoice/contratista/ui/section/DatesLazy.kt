package com.invoice.contratista.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.DateEntity

@Composable
fun DatesLazy(dateEntities: List<DateEntity>, weight: Modifier) = LazyVerticalGrid(
    modifier = weight.padding(start = 8.dp, top = 8.dp),
    columns = GridCells.Fixed(2),
    verticalArrangement = Arrangement.spacedBy(4.dp),
    horizontalArrangement = Arrangement.spacedBy(4.dp)
) {
    items(dateEntities.size) {
        ElevatedCard {
            Text(
                dateEntities[it].date,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
            )
            Text(
                dateEntities[it].name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
            )
        }
    }
}