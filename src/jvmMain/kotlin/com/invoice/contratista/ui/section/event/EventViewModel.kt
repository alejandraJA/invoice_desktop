package com.invoice.contratista.ui.section.event

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.response.event.*
import com.invoice.contratista.service.EventService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EventViewModel : KoinComponent {

    private val eventService: EventService by inject()

    var event: MutableState<EventModel?> = mutableStateOf(null)
    val budget: MutableState<BudgetEntity?> = mutableStateOf(null)
    val eventList: MutableState<List<EventModel>> = mutableStateOf(listOf())
    val addEventIndicator: MutableState<Boolean> = mutableStateOf(false)
    val loadingDialogState: MutableState<Boolean> = mutableStateOf(true)
    val errorState: MutableState<String> = mutableStateOf("")
    val customer: MutableState<CustomerEntity?> = mutableStateOf(null)
    val scheduleList: MutableState<List<ScheduleEntity>> = mutableStateOf(listOf())
    val budgetList: MutableState<List<BudgetEntity>> = mutableStateOf(listOf())
    val noteList: MutableState<List<NoteEntity>> = mutableStateOf(listOf())
    val dateList: MutableState<List<DateEntity>> = mutableStateOf(listOf())

    val listParts: MutableState<List<PartEntity>> = mutableStateOf(listOf())

    val budgetSelected = { budgetEntity: BudgetEntity? ->
        budget.value = budgetEntity
        if (budgetEntity != null)
            listParts.value = budgetEntity.partEntities
    }
    val onEventSelected = { eventModel: EventModel? ->
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