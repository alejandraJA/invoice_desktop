package com.invoice.contratista.service

import com.invoice.contratista.data.source.local.UserLogged
import com.invoice.contratista.data.source.web.models.UpdateTokenRequest
import com.invoice.contratista.domain.UserLoggedRepository

class UserService(private val repository: UserLoggedRepository) {

    fun getUpdateTokenRequest() : UpdateTokenRequest = repository.getTokenRequest()

    var token: String?
        get() = repository.getToken()
        set(value) = repository.setToken(value ?: "")

    fun isLoggedUser(): Boolean = repository.isUserLogged()

    fun login(email: String, password: String) = repository.login(email, password)
    fun singIn(userLogged: UserLogged) = repository.singIn(userLogged)
    fun logout() = repository.logout()
}