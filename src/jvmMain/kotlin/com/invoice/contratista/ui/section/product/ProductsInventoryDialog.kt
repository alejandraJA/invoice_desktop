package com.invoice.contratista.ui.section.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.invoice.contratista.utils.SELECT_PRODUCT

@ExperimentalMaterialApi
@Composable
fun ProductsInventoryDialog(onCloseDialog: () -> Unit) = Column {
    AlertDialog(
        onDismissRequest = onCloseDialog,
        title = { Text(SELECT_PRODUCT) },
        text = {
            InventoryLazy()
        },
        confirmButton = {}
    )
}