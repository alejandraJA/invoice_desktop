package com.invoice.contratista.ui.section.budget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.BudgetEntity

@ExperimentalMaterial3Api
@Composable
fun BudgetLazy(budgetEntities: List<BudgetEntity>, budgetSelected: (BudgetEntity) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.padding(top = 8.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(count = budgetEntities.size) {
            val budgetEntity = budgetEntities[it]
            BudgetData(budgetEntity, budgetSelected)
        }
    }
}

