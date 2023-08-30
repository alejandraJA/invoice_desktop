package com.invoice.contratista.domain

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.Event

interface EventRepository {
    suspend fun getAll(token: String, webStatus: WebStatus<List<Event>>)
}