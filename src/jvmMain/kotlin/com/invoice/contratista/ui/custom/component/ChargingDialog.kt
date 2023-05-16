package com.invoice.contratista.ui.custom.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun ChargingDialog(show: MutableState<Boolean>, onDismiss: () -> Unit) {
    if (show.value)
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Charging")
                    LinearProgressIndicator()
                }
            },
            shape = RoundedCornerShape(20.dp),
            backgroundColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.background,
        )
}