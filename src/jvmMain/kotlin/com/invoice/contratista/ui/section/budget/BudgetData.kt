package com.invoice.contratista.ui.section.budget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.BudgetEntity
import com.invoice.contratista.ui.theme.Alpha
import com.invoice.contratista.ui.theme.ModifierFieldImagesSmall
import com.invoice.contratista.utils.BUDGET
import com.invoice.contratista.utils.DATE
import com.invoice.contratista.utils.STATUS

@ExperimentalMaterial3Api
@Composable
fun BudgetData(
    budgetEntity: BudgetEntity,
    budgetSelected: (BudgetEntity) -> Unit,
    stateOnClick: Boolean = true
) {
    val onClick = {
        budgetSelected.invoke(budgetEntity)
    }
    if (stateOnClick) ElevatedCard(onClick) {
        ShowData(budgetEntity)
    }
    else ElevatedCard {
        ShowData(budgetEntity)
    }
}

@Composable
fun ShowData(budgetEntity: BudgetEntity) = Column {
    Row(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = BUDGET, style = MaterialTheme.typography.bodySmall, modifier = Alpha)
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource("drawables/number.svg"),
                    modifier = ModifierFieldImagesSmall,
                    contentDescription = ""
                )
                Text(text = budgetEntity.number.toString(), style = MaterialTheme.typography.bodyMedium)
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = DATE, style = MaterialTheme.typography.bodySmall, modifier = Alpha)
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource("drawables/date.svg"),
                    modifier = ModifierFieldImagesSmall,
                    contentDescription = ""
                )
                Text(text = budgetEntity.date, style = MaterialTheme.typography.bodySmall)
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = STATUS,
                style = MaterialTheme.typography.bodySmall,
                modifier = Alpha.padding(start = 16.dp),
            )
            Text(
                text = budgetEntity.status,
                modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
