package com.invoice.contratista.sys.domain.usecase

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.local.UserLogged
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.data.source.web.models.request.UpdateTokenRequest
import com.invoice.contratista.data.source.web.models.response.TokenModel
import com.invoice.contratista.sys.domain.repository.web.SingRepository
import com.invoice.contratista.sys.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginComponent() : KoinComponent {

    private val repository: SingRepository by inject()
    private val userService: UserService by inject()

    val isLoggedUser: Boolean
        get() = userService.isLoggedUser()

    suspend fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isLoggedUser) {
            if (userService.login(email, password)) {
                onError.invoke("Email or password incorrect!")
                return@withContext
            }
            val request = UpdateTokenRequest(email, userService.getToken()!!, onSuccess, onError)
            repository.updateToken(request, updateToken(onSuccess, onError))
        } else {
            repository.singIn(SingRequest(email, password), singIn(email, password, onSuccess, onError))
        }
    }

    fun logout() = userService.logout()

    private fun singIn(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) =
        object : WebStatus<TokenModel?> {
            override fun success(data: TokenModel?) {
                userService.singIn(UserLogged(email, data!!.token, password))
                onSuccess.invoke()
            }

            override fun error(e: String) = onError.invoke(e)

        }

    private fun updateToken(onSuccess: () -> Unit, onError: (String) -> Unit) = object : WebStatus<TokenModel> {
        override fun success(data: TokenModel) {
            userService.setToken(data.token)
            onSuccess.invoke()
        }

        override fun error(e: String) = onError.invoke(e)

    }

}