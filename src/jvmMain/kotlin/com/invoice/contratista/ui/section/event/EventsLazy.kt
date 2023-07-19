package com.invoice.contratista.ui.section.event

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.EventModel
import com.invoice.contratista.service.EventService
import com.invoice.contratista.ui.custom.component.ErrorDialog
import com.invoice.contratista.ui.custom.component.LoadingDialog
import com.invoice.contratista.ui.theme.ModifierFieldImages
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun EventLazy(
    eventService: EventService,
    eventSelected: (EventModel?) -> Unit,
    addEvent: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val list = remember { mutableStateOf(listOf<EventModel>()) }
    val loadingDialogState = rememberSaveable { mutableStateOf(true) }
    val errorState = rememberSaveable { mutableStateOf("") }
    scope.launch {
        eventService.getAll({
            list.value = it
            loadingDialogState.value = false
        }, { errorState.value = it })
    }
    Text(text = "Open Events", style = MaterialTheme.typography.titleLarge, modifier = Modifier.width(300.dp))
    ElevatedButton(onClick = addEvent, modifier = Modifier.width(300.dp)) {
        Icon(
            painter = painterResource("drawables/add.svg"),
            contentDescription = "Add event",
            modifier = ModifierFieldImages
        )
        Text(text = "Add event")
    }
    LazyColumn(modifier = Modifier.width(300.dp).padding(top = 8.dp)) {
        items(count = list.value.size) {
            EventItem(
                list.value[it],
                modifier = if (it == list.value.size - 1) Modifier.padding(bottom = 16.dp)
                else Modifier.padding(bottom = 8.dp),
                eventSelected
            )
        }
    }
    LoadingDialog(show = loadingDialogState) { loadingDialogState.value = false }
    ErrorDialog(errorState) {
        errorState.value = ""
    }
}