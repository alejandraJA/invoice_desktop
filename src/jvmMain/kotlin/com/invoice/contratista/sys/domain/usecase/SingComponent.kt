package com.invoice.contratista.sys.domain.usecase

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.local.UserLogged
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.data.source.web.models.request.UpdateTokenRequest
import com.invoice.contratista.data.source.web.models.response.TokenModel
import com.invoice.contratista.data.source.web.models.response.UserModel
import com.invoice.contratista.sys.domain.repository.web.SingRepository
import com.invoice.contratista.sys.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class SingComponent() : KoinComponent {

    private val repository: SingRepository by inject()
    private val userService: UserService by inject()

    val isLoggedUser: Boolean
        get() = userService.isLoggedUser()

    suspend fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
        context: CoroutineContext = Dispatchers.IO,
    ) = withContext(context =  context) {
        if (isLoggedUser) {
            if (userService.login(email, password)) {
                onError.invoke("Email or password incorrect!")
                return@withContext
            }
            val request = UpdateTokenRequest(email, userService.getToken()!!, onSuccess, onError)
            repository.updateToken(request, updateToken(onSuccess, onError))
            return@withContext
        }
        repository.singIn(SingRequest(email, password), singIn(email, password, onSuccess, onError))
    }

    suspend fun singUp(
        request: SingRequest,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
        context: CoroutineContext = Dispatchers.IO,
    ) = withContext(context =  context) {
        repository.singUp(request, singUpWebStatus(onSuccess, onError))
    }

    fun logout() = userService.logout()
    private fun singIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = object : WebStatus<TokenModel?> {
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

    private fun singUpWebStatus(onSuccess: () -> Unit, onError: (String) -> Unit) =
        object : WebStatus<UserModel> {
            override fun success(data: UserModel) {
                onSuccess.invoke()
            }

            override fun error(e: String) = onError.invoke(e)
        }

}