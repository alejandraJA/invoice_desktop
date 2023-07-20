package com.invoice.contratista

import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.service.SingService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppViewModel : KoinComponent {
    private val singService: SingService by inject()

    val isLoggedUser =  mutableStateOf(singService.isLoggedUser)
    val updateToken =  mutableStateOf(false)

    suspend fun updateToken() = singService.updateToken {
        updateToken.value = true
    }
}