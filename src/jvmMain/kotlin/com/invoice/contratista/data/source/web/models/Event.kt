package com.invoice.contratista.data.source.web.models

data class Event(
    val id: String,
    val state: String,
    val note: String,
    val eventName: String,
    val date: String,
    val budgetEntities: List<Budget>,
    val noteEntities: List<Note>,
    val scheduleEntities: List<Schedule>,
    val dateEntities: List<Date>,
    val customerEntity: Customer,
)