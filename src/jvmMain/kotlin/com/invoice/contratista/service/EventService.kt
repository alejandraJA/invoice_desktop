package com.invoice.contratista.service

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.response.event.EventModel
import com.invoice.contratista.domain.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventService(
    private val repository: EventRepository,
    private val userService: UserService
) {

    private val isUserLogged: Boolean
        get() = userService.isLoggedUser()
    private val token: String?
        get() = userService.getToken()

    suspend fun getAll(
        onSuccess: (List<EventModel>) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged && token!!.isNotEmpty()) {
            repository.getAll(token!!, getWebStatus(onSuccess, onError))
        }
    }

    private fun getWebStatus(onSuccess: (List<EventModel>) -> Unit, onError: (String) -> Unit) = object : WebStatus<List<EventModel>> {
        override fun success(data: List<EventModel>) {
            if (data.isNotEmpty())onSuccess.invoke(data)
            else onError.invoke("Empty list")
        }

        override fun error(e: String) {
            onError.invoke(e)
        }
    }
}