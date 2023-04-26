package com.invoice.contratista.utils

object Constants {

    enum class Status { Success, Failure, Loading }

    enum class Priority {
        Low,
        Medium,
        Important,
        Urgent
    }

    enum class Steps {
        Event,
        Budget,
        Receipt,
        Invoice
    }

    enum class EventStatus {
        Start,
        Process,
        Finish,
    }

    enum class StateEvent {
        Creado,
        Levantado,
        Cotizando,
        Enviado,
        Aprobado,
        Cancelado,
        Pendiente,
        Realizado,
        Finiquitado
    }

    enum class StateSchedule { Pendiente, Atendido }

    enum class BudgetStatus {
        Pendiente,
        Espera,
        Cancelado,
        Autorizado,
    }

    const val BASE_URL = "http://localhost:8080/"
    const val AUTHORIZATION = "Authorization"
    const val ID_SCHEDULE = "idSchedule"
    const val ID_NOTE = "idNote"
    const val ID_CUSTOMER = "idCustomer"
    const val ID_BUDGET = "idBudget"
    const val ID_PART = "idPart"
    const val ID_EVENT = "idEvent"
    const val ID_USER = "idUser"
    const val ID_RESERVED: String = "idReserved"
    const val ID_PRODUCT = "idProduct"
}