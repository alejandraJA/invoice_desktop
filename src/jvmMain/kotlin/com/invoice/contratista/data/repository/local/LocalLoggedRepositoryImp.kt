package com.invoice.contratista.data.repository.local

import com.invoice.contratista.data.source.local.UserLogged
import com.invoice.contratista.sys.domain.repository.local.UserLoggedRepository

class LocalLoggedRepositoryImp : UserLoggedRepository {

    private var _userLogged: UserLogged? = null
    override fun get() = _userLogged

    override fun login(userLogged: UserLogged) {
        _userLogged = userLogged
    }

    override fun logout() {
        _userLogged = null
    }

    override fun getToken() = _userLogged?.token

    override fun setToken(token: String) {
        _userLogged?.token = token
    }

    override fun isUserLogged() = _userLogged != null

}