package com.invoice.contratista.ui.section.budget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.ui.custom.component.ErrorDialog
import com.invoice.contratista.ui.custom.component.LoadingDialog
import com.invoice.contratista.ui.section.CustomerDataSection
import com.invoice.contratista.ui.section.event.EventViewModel
import com.invoice.contratista.ui.section.part.PartLazy
import com.invoice.contratista.ui.section.part.PartSection
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.utils.*
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun BudgetSection(eventViewModel: EventViewModel) = Column {
    val scope = rememberCoroutineScope()
    val viewModel = remember { BudgetViewModel() }
    if (eventViewModel.listParts.value.isNotEmpty())
        viewModel.part.value = eventViewModel.listParts.value[0]
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
    Row {
        Column(modifier = Modifier.weight(0.5f)) {
            // region Body Budget
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
                scope.launch {
                    viewModel.findByProductId(partEntity)
                }
            }
            // endregion
        }
        viewModel.part.value?.let {
            PartSection(
                viewModel = viewModel,
                modifier = Modifier.weight(1f),
            )
        }
    }
    LoadingDialog(show = viewModel.loadingDialogState) { viewModel.loadingDialogState.value = false }
    ErrorDialog(viewModel.errorState) {
        viewModel.errorState.value = ""
    }
}

