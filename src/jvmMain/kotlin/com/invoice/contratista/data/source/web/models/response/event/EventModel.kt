package com.invoice.contratista.data.source.web.models.response.event

data class EventModel(
    val budgetEntities: List<BudgetEntity>,
    val customerEntity: CustomerEntity,
    val date: String,
    val dateEntities: List<DateEntity>,
    val eventName: String,
    val id: String,
    val note: String,
    val noteEntities: List<NoteEntity>,
    val scheduleEntities: List<ScheduleEntity>,
    val state: String
)