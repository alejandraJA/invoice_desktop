package com.invoice.contratista.data.repository.local

import com.invoice.contratista.data.source.local.UserLogged
import com.invoice.contratista.data.source.web.models.request.UpdateTokenRequest
import com.invoice.contratista.domain.UserLoggedRepository
import java.util.prefs.Preferences

class LocalLoggedRepositoryImp : UserLoggedRepository {

    private val preferences = Preferences.userNodeForPackage(LocalLoggedRepositoryImp::class.java)

    private var _email: String
        get() = preferences.get(UserConstants.USERNAME, "")
        set(value) = preferences.put(UserConstants.USERNAME, value)

    private var _token: String
        get() = preferences.get("${_email}.${UserConstants.TOKEN}", "")
        set(value) = preferences.put("${_email}.${UserConstants.TOKEN}", value)

    private var _password: String
        get() = preferences.get("$_email.${UserConstants.PASSWORD}", "")
        set(value) = preferences.put("$_email.${UserConstants.PASSWORD}", value)

    override fun get() = UserLogged(email = _email, token = _token, password = _password)

    override fun singIn(userLogged: UserLogged) {
        _email = userLogged.email
        _token = userLogged.token
        _password = userLogged.password
    }

    override fun logout() {
        _token = ""
        _email = ""
        _password = ""
    }

    override fun getToken() = _token

    override fun setToken(token: String) {
        this._token = token
    }

    override fun isUserLogged() = _token.isNotEmpty() && _email.isNotEmpty() && _password.isNotEmpty()
    override fun login(email: String, password: String): Boolean =
        this._email == email && this._password == password

    override fun getTokenRequest(): UpdateTokenRequest = UpdateTokenRequest(_email, _token)

}

object UserConstants {
    const val USERNAME = "username"
    const val PASSWORD = "password"
    const val TOKEN = "token"
}