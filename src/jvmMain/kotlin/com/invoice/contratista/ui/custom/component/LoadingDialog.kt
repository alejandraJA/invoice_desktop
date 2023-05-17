package com.invoice.contratista.ui.custom.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.theme.ModifierFill

@ExperimentalMaterialApi
@Composable
fun LoadingDialog(show: MutableState<Boolean>, onDismiss: () -> Unit) {
    if (show.value)
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
            },
            title = {
                Row(
                    modifier = ModifierFill,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(modifier = Modifier.width(30.dp))
                    Text("Loading...", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(start = 8.dp))
                }
            },
            shape = RoundedCornerShape(20.dp),
            backgroundColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.background,
        )
}