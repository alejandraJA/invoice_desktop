package com.invoice.contratista.sys.service

import com.invoice.contratista.data.source.local.UserLogged
import com.invoice.contratista.sys.domain.repository.local.UserLoggedRepository

class UserService(private val repository: UserLoggedRepository) {
    fun getToken() = repository.getToken()
    fun setToken(token: String) = repository.setToken(token)

    fun isLoggedUser() = repository.isUserLogged()

    fun login(email: String, password: String) = repository.login(email, password)
    fun singIn(userLogged: UserLogged) = repository.singIn(userLogged)
    fun logout() = repository.logout()
    fun delete() = repository.logout()
}