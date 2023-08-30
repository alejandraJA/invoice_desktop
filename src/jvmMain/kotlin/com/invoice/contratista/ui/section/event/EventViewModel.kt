package com.invoice.contratista.ui.section.event

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.Event
import com.invoice.contratista.data.source.web.models.*
import com.invoice.contratista.service.EventService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EventViewModel : KoinComponent {

    private val eventService: EventService by inject()

    var event: MutableState<Event?> = mutableStateOf(null)
    val budget: MutableState<Budget?> = mutableStateOf(null)
    val eventList: MutableState<List<Event>> = mutableStateOf(listOf())
    val addEventIndicator: MutableState<Boolean> = mutableStateOf(false)
    val loadingDialogState: MutableState<Boolean> = mutableStateOf(true)
    val errorState: MutableState<String> = mutableStateOf("")
    val customer: MutableState<Customer?> = mutableStateOf(null)
    val scheduleList: MutableState<List<Schedule>> = mutableStateOf(listOf())
    val budgetList: MutableState<List<Budget>> = mutableStateOf(listOf())
    val noteList: MutableState<List<Note>> = mutableStateOf(listOf())
    val dateList: MutableState<List<Date>> = mutableStateOf(listOf())

    val listParts: MutableState<List<Part>> = mutableStateOf(listOf())

    val budgetSelected = { budget: Budget? ->
        this.budget.value = budget
        if (budget != null)
            listParts.value = budget.partEntities
    }
    val onEventSelected = { eventModel: Event? ->
        if (eventModel != null) {
            customer.value = eventModel.customerEntity
            scheduleList.value = eventModel.scheduleEntities
            budgetList.value = eventModel.budgetEntities
            noteList.value = eventModel.noteEntities
            dateList.value = eventModel.dateEntities
        }
        event.value = eventModel
    }
    val onAddEvent = { addEventIndicator.value = true }

    suspend fun getAll() = eventService.getAll({
        eventList.value = it
        loadingDialogState.value = false
    }, { errorState.value = it })

}