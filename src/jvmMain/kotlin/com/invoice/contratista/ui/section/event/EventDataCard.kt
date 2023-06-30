package com.invoice.contratista.ui.section.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.EventModel
import com.invoice.contratista.ui.custom.component.TextField
import com.invoice.contratista.ui.custom.component.TextFieldModel
import com.invoice.contratista.ui.section.CustomerDataSection
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import com.invoice.contratista.utils.CUSTOMER_DATA
import com.invoice.contratista.utils.EDIT_NOTE
import com.invoice.contratista.utils.ENTER_NOTE
import com.invoice.contratista.utils.NOTE

@ExperimentalMaterial3Api
@Composable
fun EventDataCard(event: EventModel) {
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
    ElevatedCard(modifier = Modifier.padding(bottom = 16.dp)) { CustomerDataSection(event.customerEntity) }
}