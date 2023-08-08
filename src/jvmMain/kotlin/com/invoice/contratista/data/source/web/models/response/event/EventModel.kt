package com.invoice.contratista.data.source.web.models.response.event

data class EventModel(
    val id: String,
    val state: String,
    val note: String,
    val eventName: String,
    val date: String,
    val budgetEntities: List<BudgetEntity>,
    val noteEntities: List<NoteEntity>,
    val scheduleEntities: List<ScheduleEntity>,
    val dateEntities: List<DateEntity>,
    val customerEntity: CustomerEntity,
)