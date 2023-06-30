package com.invoice.contratista.ui.section.budget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.BudgetEntity
import com.invoice.contratista.data.source.web.models.response.event.CustomerEntity
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.ui.section.CustomerDataSection
import com.invoice.contratista.ui.section.part.PartLazy
import com.invoice.contratista.ui.section.part.PartSection
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.utils.*

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun BudgetSection(
    budget: BudgetEntity,
    budgetSelected: (BudgetEntity?) -> Unit,
    customer: CustomerEntity
) = Column {
    val part = remember { mutableStateOf<PartEntity?>(null) }
    if (budget.partEntities.isNotEmpty()) part.value = budget.partEntities[0]
    Row {
        ElevatedButton(
            onClick = { budgetSelected.invoke(null) },
        ) {
            Icon(
                painter = painterResource("drawables/back.svg"),
                contentDescription = BACK,
                modifier = ModifierFieldImages
            )
        }
        Text(
            text = BUDGET_SECTION,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(1f).padding(start = 16.dp)
        )
    }
    Row {
        Column(modifier = Modifier.weight(0.7f)) {
            BudgetData(budget, budgetSelected, false)
            ElevatedCard(modifier = Modifier.padding(top = 4.dp)) { CustomerDataSection(customer) }
            ElevatedButton(onClick = {}, modifier = ModifierFill.padding(top = 4.dp, bottom = 4.dp)) {
                Text(text = ADD_PART)
            }
            PartLazy(budget.partEntities) {
                part.value = it
            }
        }
        PartSection(part.value, modifier = Modifier.weight(0.5f))
        ElevatedCard(modifier = Modifier.weight(1f)) { }
    }
}

