package com.invoice.contratista.ui.section.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.EventModel
import com.invoice.contratista.ui.theme.ModifierPaddingScreen

@ExperimentalMaterial3Api
@Composable
fun SetEventItem(eventModel: EventModel) = ElevatedCard(modifier = Modifier.padding(top = 8.dp)) {
    Column(modifier = ModifierPaddingScreen) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = eventModel.eventName,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = eventModel.state,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Text(
            text = eventModel.note,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 16.dp)
        )
        LazyRow {
            items(eventModel.budgetEntities.size) {
                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = "COT-${eventModel.budgetEntities[it].number}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    leadingIcon = {},
                    modifier = if (it == 0) Modifier.padding(start = 0.dp) else Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
