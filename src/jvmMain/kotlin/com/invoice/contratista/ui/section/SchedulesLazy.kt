package com.invoice.contratista.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.Schedule
import com.invoice.contratista.utils.getAddress

@Composable
fun SchedulesLazy(scheduleEntities: List<Schedule>, weight: Modifier) = LazyColumn(
    modifier = weight.padding(end = 8.dp, top = 8.dp),
    verticalArrangement = Arrangement.spacedBy(4.dp),
) {
    items(scheduleEntities.size) {
        val schedule = scheduleEntities[it]
        ElevatedCard {
            Text(text = schedule.name, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
            Row(modifier = Modifier.padding(top = 4.dp, start = 16.dp, end = 16.dp)) {
                Text(text = schedule.state, modifier = Modifier.weight(1f))
                Text(text = schedule.date, modifier = Modifier.weight(1f))
            }
            Text(text = schedule.note, Modifier.padding(top = 4.dp, start = 16.dp, end = 16.dp))
            Text(
                text = schedule.address.getAddress(),
                Modifier.padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
    }
}