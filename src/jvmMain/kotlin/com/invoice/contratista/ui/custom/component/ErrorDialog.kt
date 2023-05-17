package com.invoice.contratista.ui.custom.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.theme.ModifierFieldImages
import com.invoice.contratista.theme.ModifierFill

@ExperimentalMaterialApi
@Composable
fun ErrorDialog(errorFromApi: MutableState<String>, onDismiss: () -> Unit) {
    if (errorFromApi.value.isNotEmpty()) AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Accept")
            }
        },
        title = {
            Row(
                modifier = ModifierFill,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource("drawables/bug.svg"),
                    contentDescription = "bug",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = ModifierFieldImages
                )
                Text(text = "Ups!", style = MaterialTheme.typography.titleMedium)
            }
        },
        text = {
            Text(text = errorFromApi.value, style = MaterialTheme.typography.bodyMedium, modifier = ModifierFill)
        },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.width(300.dp)
    )
}
