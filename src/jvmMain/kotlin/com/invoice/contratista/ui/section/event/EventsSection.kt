@file:OptIn(ExperimentalMaterialApi::class)

package com.invoice.contratista.ui.section.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.invoice.contratista.sys.domain.usecase.EventComponent
import com.invoice.contratista.ui.custom.component.ErrorDialog
import com.invoice.contratista.ui.custom.component.LoadingDialog
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierPaddingScreen2
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EventSection() = Column(
    modifier = ModifierPaddingScreen2.fillMaxHeight().fillMaxWidth(),
) {
    val eventComponent = EventComponent()
    val scope = rememberCoroutineScope()
    val list = remember { mutableStateOf(listOf<EventModel>()) }
    val loadingDialogState = rememberSaveable { mutableStateOf(true) }
    val errorState = rememberSaveable { mutableStateOf("") }
    scope.launch {
        eventComponent.getAll({
            list.value = it
            loadingDialogState.value = false
        }, { errorState.value = it })
    }
    Text(text = "Open Events", style = MaterialTheme.typography.titleLarge, modifier = Modifier.width(300.dp))
    ElevatedButton(onClick = {}, modifier = Modifier.width(300.dp)) {
        Icon(
            painter = painterResource("drawables/add.svg"),
            contentDescription = "Add event",
            modifier = ModifierFieldImages
        )
        Text(text = "Add event")
    }
    LazyColumn(modifier = Modifier.width(300.dp)) {
        items(count = list.value.size) {
            SetEventItem(list.value[it])
        }
    }
    LoadingDialog(show = loadingDialogState) { loadingDialogState.value = false }
    ErrorDialog(errorState) {
        errorState.value = ""
    }
}
