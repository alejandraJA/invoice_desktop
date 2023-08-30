package com.invoice.contratista.domain

import com.invoice.contratista.data.source.local.UserLogged
import com.invoice.contratista.data.source.web.models.UpdateTokenRequest

interface UserLoggedRepository {
    fun get(): UserLogged?
    fun singIn(userLogged: UserLogged)
    fun logout()
    fun getToken(): String?
    fun setToken(token: String)
    fun isUserLogged(): Boolean
    fun login(email: String, password: String): Boolean
    fun getTokenRequest(): UpdateTokenRequest
}