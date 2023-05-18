package com.invoice.contratista.data.repository.local

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.invoice.contratista.data.source.local.UserLogged
import com.invoice.contratista.sys.domain.repository.local.UserLoggedRepository
import java.util.prefs.Preferences

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
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
        _token = userLogged.token
        _email = userLogged.email
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

    override fun isUserLogged() = _token.isNotEmpty()
    override fun login(email: String, password: String): Boolean =
        this._email == email && this._password == password

}

object UserConstants {
    const val NAME = "user"
    const val USERNAME = "username"
    const val PASSWORD = "password"
    const val TOKEN = "token"
}