package com.invoice.contratista.ui.section.budget

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.ui.section.CustomerDataSection
import com.invoice.contratista.ui.section.event.EventViewModel
import com.invoice.contratista.ui.section.part.PartLazy
import com.invoice.contratista.ui.section.part.PartSection
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.utils.*

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun BudgetSection(eventViewModel: EventViewModel) = Column {
    val viewModel = remember { BudgetViewModel() }
    if (eventViewModel.listParts.value.isNotEmpty() && viewModel.part.value == null)
            viewModel.setPart(eventViewModel.listParts.value[0])
    // region Head
    Row {
        Row(modifier = Modifier.weight(0.5f)) {
            ElevatedButton(
                onClick = { eventViewModel.budgetSelected.invoke(null) },
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
        Row(modifier = Modifier.weight(1f)) {
            Text(
                text = PART_SECTION,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp).weight(1f)
            )
            Text(
                text = PRODUCT_SECTION,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp).weight(1f)
            )
        }
    }
    // endregion
    Row {
        // region Body Budget
        Column(modifier = Modifier.weight(0.5f)) {
            BudgetData(eventViewModel = eventViewModel, stateOnClick = false)
            ElevatedCard(modifier = Modifier.padding(top = 4.dp)) {
                eventViewModel.customer.value?.let {
                    CustomerDataSection(it)
                }
            }
            ElevatedButton(onClick = {}, modifier = ModifierFill.padding(top = 4.dp, bottom = 4.dp)) {
                Text(text = ADD_PART)
            }
            PartLazy(eventViewModel.listParts.value) { partEntity ->
                viewModel.setPart(partEntity)
            }
        }
        // endregion
        Divider(modifier = Modifier.width(1.dp).fillMaxHeight().padding(8.dp))
        viewModel.part.value?.let {
            PartSection(
                part = viewModel.part,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

