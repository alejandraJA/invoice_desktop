package com.invoice.contratista.ui.section.event

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.ui.custom.component.ErrorDialog
import com.invoice.contratista.ui.custom.component.LoadingDialog
import com.invoice.contratista.ui.theme.ModifierFieldImages
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun EventLazy(
    eventViewModel: EventViewModel,
) {
    val scope = rememberCoroutineScope()
    scope.launch {
        eventViewModel.getAll()
    }
    Text(text = "Open Events", style = MaterialTheme.typography.titleLarge, modifier = Modifier.width(300.dp))
    ElevatedButton(onClick = eventViewModel.onAddEvent, modifier = Modifier.width(300.dp)) {
        Icon(
            painter = painterResource("drawables/add.svg"),
            contentDescription = "Add event",
            modifier = ModifierFieldImages
        )
        Text(text = "Add event")
    }
    LazyColumn(modifier = Modifier.width(300.dp).padding(top = 8.dp)) {
        items(count = eventViewModel.eventList.value.size) {
            EventItem(
                eventViewModel.eventList.value[it],
                modifier = if (it == eventViewModel.eventList.value.size - 1) Modifier.padding(bottom = 16.dp)
                else Modifier.padding(bottom = 8.dp),
                eventViewModel.onEventSelected
            )
        }
    }
    LoadingDialog(show = eventViewModel.loadingDialogState) { eventViewModel.loadingDialogState.value = false }
    ErrorDialog(eventViewModel.errorState) {
        eventViewModel.errorState.value = ""
    }
}