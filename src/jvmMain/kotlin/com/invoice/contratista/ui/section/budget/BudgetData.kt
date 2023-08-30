package com.invoice.contratista.ui.section.budget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.Budget
import com.invoice.contratista.ui.custom.component.TextWithTitle
import com.invoice.contratista.ui.section.event.EventViewModel
import com.invoice.contratista.ui.theme.ModifierCard
import com.invoice.contratista.utils.BUDGET
import com.invoice.contratista.utils.DATE
import com.invoice.contratista.utils.STATUS

@ExperimentalMaterial3Api
@Composable
fun BudgetData(
    eventViewModel: EventViewModel,
    budget: Budget = eventViewModel.budget.value!!,
    stateOnClick: Boolean = true
) {
    val onClick = {
        eventViewModel.budgetSelected.invoke(budget)
    }
    if (stateOnClick) ElevatedCard(onClick) {
        ShowData(budget)
    }
    else ElevatedCard {
        ShowData(budget)
    }
}

@Composable
fun ShowData(budget: Budget) = Column {
    Row(modifier = ModifierCard) {
        TextWithTitle(
            title = BUDGET,
            text = budget.number.toString(),
            modifier = Modifier.weight(1f / 2).padding(end = 4.dp),
            iconResource = "drawables/number.svg",
        )
        TextWithTitle(
            title = DATE,
            text = budget.date,
            modifier = Modifier.weight(1f).padding(start = 4.dp, end = 4.dp),
            iconResource = "drawables/date.svg",
        )
        TextWithTitle(
            title = STATUS,
            text = budget.status,
            modifier = Modifier.weight(1f).padding(start = 4.dp),
        )
    }
}
