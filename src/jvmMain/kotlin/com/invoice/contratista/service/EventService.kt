package com.invoice.contratista.service

import com.invoice.contratista.data.source.web.models.response.event.EventModel
import com.invoice.contratista.domain.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventService(
    private val repository: EventRepository,
    userService: UserService
): SuperService(userService) {

    suspend fun getAll(
        onSuccess: (List<EventModel>) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.getAll(token!!, getWebStatus(onSuccess, onError))
        }
    }

}