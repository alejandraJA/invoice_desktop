package com.invoice.contratista.ui.section.event

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.*
import com.invoice.contratista.ui.section.DatesLazy
import com.invoice.contratista.ui.section.NotesLazy
import com.invoice.contratista.ui.section.SchedulesLazy
import com.invoice.contratista.ui.section.budget.BudgetsSection
import com.invoice.contratista.utils.*

@ExperimentalMaterial3Api
@Composable
fun EventSection(event: EventModel, budgetSelected: (BudgetEntity) -> Unit) = Column {
    Text(
        text = EVENT_DATA,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Row(modifier = Modifier.fillMaxHeight()) {
        LazyColumn(modifier = Modifier.width(300.dp).padding(end = 8.dp)) {
            item {
                EventDataCard(event)
            }
        }

        Divider(modifier = Modifier.fillMaxHeight().width(1.dp))

        Column(modifier = Modifier.weight(1f).padding(start = 8.dp, end = 8.dp)) {
            BudgetsSection(event, Modifier.weight(1f), budgetSelected)
            Divider(modifier = Modifier.fillMaxWidth().height(1.dp))
            Row(Modifier.weight(1f).fillMaxWidth()) {
                SchedulesLazy(event.scheduleEntities, Modifier.weight(1f))
                Divider(modifier = Modifier.fillMaxHeight().width(1.dp))
                NotesLazy(event.noteEntities, Modifier.weight(1f))
                Divider(modifier = Modifier.fillMaxHeight().width(1.dp))
                DatesLazy(event.dateEntities, Modifier.weight(1f))
            }
        }
    }
}

