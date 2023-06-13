package com.invoice.contratista.ui.section.event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.*
import com.invoice.contratista.ui.custom.component.TextField
import com.invoice.contratista.ui.custom.component.TextFieldModel
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import com.invoice.contratista.utils.*
import com.invoice.contratista.utils.AddressUtils.getAddress

@ExperimentalMaterial3Api
@Composable
fun EventSection(event: EventModel) = Column {
    Text(
        text = EVENT_DATA,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Row(modifier = Modifier.fillMaxHeight()) {
        LazyColumn(modifier = Modifier.width(300.dp).padding(end = 8.dp)) {
            item {
                ShowEventData(event)
            }
        }

        Divider(modifier = Modifier.fillMaxHeight().width(1.dp))

        Column(modifier = Modifier.weight(1f).padding(start = 8.dp, end = 8.dp)) {
            BudgetsSection(event, Modifier.weight(1f))
            Row(Modifier.weight(1f).fillMaxWidth()) {
                ShowSchedules(event.scheduleEntities, Modifier.weight(1f))
                ShowNotes(event.noteEntities, Modifier.weight(1f))
                ShowDates(event.dateEntities, Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ShowDates(dateEntities: List<DateEntity>, weight: Modifier) = LazyVerticalGrid(
    modifier = weight,
    columns = GridCells.Fixed(2),
    verticalArrangement = Arrangement.spacedBy(4.dp),
    horizontalArrangement = Arrangement.spacedBy(4.dp)
) {
    items(dateEntities.size) {
        ElevatedCard(modifier = ModifierPaddingScreen) {
            Text(dateEntities[it].date, style = MaterialTheme.typography.bodySmall)
            Text(dateEntities[it].name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun ShowNotes(noteEntities: List<NoteEntity>, weight: Modifier) = LazyColumn(modifier = weight) {

}

@Composable
fun ShowSchedules(scheduleEntities: List<ScheduleEntity>, weight: Modifier) = LazyColumn(modifier = weight) {

}

@Composable
fun BudgetsSection(event: EventModel, modifier: Modifier) = Column(modifier = modifier) {
    ElevatedButton({}, modifier = ModifierFill) {
        Text(text = ADD_BUDGET)
    }
    ShowBudgets(event.budgetEntities)
}

@Composable
fun ShowBudgets(budgetEntities: List<BudgetEntity>) {
    LazyVerticalGrid(
        modifier = Modifier.padding(top = 8.dp),
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)

    ) {
        items(count = budgetEntities.size) {
            val budgetEntity = budgetEntities[it]
            ElevatedCard {
                Row(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                    Column {
                        Text(text = BUDGET, style = MaterialTheme.typography.bodySmall)
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource("drawables/number.svg"),
                                modifier = ModifierFieldImages,
                                contentDescription = ""
                            )
                            Text(text = budgetEntity.number.toString(), style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                    Column(modifier = Modifier.weight(1f).padding(start = 16.dp)) {
                        Text(text = DATE, style = MaterialTheme.typography.bodySmall)
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource("drawables/date.svg"),
                                modifier = ModifierFieldImages,
                                contentDescription = ""
                            )
                            Text(text = budgetEntity.date, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
                Text(
                    text = STATUS,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                )
                Text(
                    text = budgetEntity.status,
                    modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ShowEventData(event: EventModel) {
    ElevatedCard(modifier = Modifier.padding(bottom = 8.dp)) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = ModifierPaddingScreen
        ) {
            Text(
                text = event.eventName,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = event.state,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
    TextField(
        textFieldModel = TextFieldModel(
            hint = NOTE,
            change = {},
            initField = event.note,
            placeholder = ENTER_NOTE,
            icon = EDIT_NOTE,
            counterEnable = true,
            counterNumber = 255
        )
    )
    Text(
        text = CUSTOMER_DATA,
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier.padding(top = 16.dp)
    )
    ElevatedCard(modifier = Modifier.padding(bottom = 16.dp)) { ShowCustomerData(event.customerEntity) }
}

@Composable
fun ShowCustomerData(customerEntity: CustomerEntity) = Column(
    modifier = ModifierPaddingScreen.fillMaxWidth()
) {
    Text(text = LEGAL_NAME, style = MaterialTheme.typography.labelMedium)
    Text(text = customerEntity.legalName, style = MaterialTheme.typography.bodySmall)
    Row {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = TAX_IDENTIFICATION,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(text = customerEntity.taxId, style = MaterialTheme.typography.bodySmall)
            Text(
                text = FISCAL_REGIME,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(text = customerEntity.taxSystem, style = MaterialTheme.typography.bodySmall)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = EMAIL, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(top = 8.dp))
            Text(text = customerEntity.email, style = MaterialTheme.typography.bodySmall)
            Text(text = PHONE, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(top = 8.dp))
            Text(text = customerEntity.phone, style = MaterialTheme.typography.bodySmall)
        }
    }
    Text(text = ADDRESS, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(top = 8.dp))
    Text(text = customerEntity.addressEntity.getAddress(), style = MaterialTheme.typography.bodySmall)
}