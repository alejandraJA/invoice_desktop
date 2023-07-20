package com.invoice.contratista.ui.section.budget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.ui.section.event.EventViewModel
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.utils.ADD_BUDGET
import com.invoice.contratista.utils.FILTER_LIST

@ExperimentalMaterial3Api
@Composable
fun BudgetsSection(
    modifier: Modifier,
    eventViewModel: EventViewModel,
) = Column(modifier = modifier) {
    Row {
        ElevatedButton(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = ADD_BUDGET)
        }
        ElevatedButton(onClick = {}, modifier = Modifier.padding(start = 8.dp)) {
            Icon(
                painter = painterResource("drawables/filter_list.svg"), contentDescription = FILTER_LIST,
                modifier = ModifierFieldImages
            )
        }
    }
    BudgetLazy(eventViewModel)
}