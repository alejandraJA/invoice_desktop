package com.invoice.contratista.service

import com.invoice.contratista.data.source.local.UserLogged
import com.invoice.contratista.data.source.web.models.request.UpdateTokenRequest
import com.invoice.contratista.domain.UserLoggedRepository

class UserService(private val repository: UserLoggedRepository) {

    fun getUpdateTokenRequest() : UpdateTokenRequest= repository.getTokenRequest()
    fun getToken() = repository.getToken()

    fun isLoggedUser(): Boolean = repository.isUserLogged()

    fun login(email: String, password: String) = repository.login(email, password)
    fun singIn(userLogged: UserLogged) = repository.singIn(userLogged)
    fun logout() = repository.logout()
}