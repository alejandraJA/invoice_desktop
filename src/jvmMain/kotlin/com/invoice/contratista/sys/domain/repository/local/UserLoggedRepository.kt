package com.invoice.contratista.sys.domain.repository.local

import com.invoice.contratista.data.source.local.UserLogged

interface UserLoggedRepository {
    fun get(): UserLogged?
    fun singIn(userLogged: UserLogged)
    fun logout()
    fun getToken(): String?
    fun setToken(token: String)
    fun isUserLogged(): Boolean
    fun login(email: String, password: String): Boolean
}