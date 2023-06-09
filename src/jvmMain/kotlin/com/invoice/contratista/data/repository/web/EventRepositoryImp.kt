package com.invoice.contratista.data.repository.web

import com.invoice.contratista.data.repository.web.utils.Resolve
import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.event.EventModel
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.sys.domain.repository.web.EventRepository

class EventRepositoryImp(private val service: Service) : EventRepository {
    override suspend fun getAll(
        token: String,
        webStatus: WebStatus<List<EventModel>>
    ): Unit = Resolve(service.getAllEvents(token), webStatus).invoke()
}