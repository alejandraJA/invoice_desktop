package com.invoice.contratista.service

import com.invoice.contratista.data.source.local.UserLogged
import com.invoice.contratista.data.source.web.models.SingRequest
import com.invoice.contratista.data.source.web.models.Token
import com.invoice.contratista.data.source.web.models.UpdateTokenRequest
import com.invoice.contratista.data.source.web.models.User
import com.invoice.contratista.domain.SingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SingService(
    private val repository: SingRepository,
    private val userService: UserService
) : SuperService(userService) {

    suspend fun updateToken(success: (Token) -> Unit, onError: (String) -> Unit) = withContext(Dispatchers.IO) {
        if (isUserLogged)
            repository.updateToken(
                userService.getUpdateTokenRequest(),
                getWebStatus(success, onError)
            )
    }

    suspend fun login(
        email: String,
        password: String,
        onSuccess: (Token) -> Unit,
        onError: (String) -> Unit,
        context: CoroutineContext = Dispatchers.IO,
    ) = withContext(context = context) {
        if (isUserLogged) {
            if (userService.login(email, password)) {
                onError.invoke("Email or password incorrect!")
                return@withContext
            }
            val request = UpdateTokenRequest(email, token!!)
            repository.updateToken(request, getWebStatus(onSuccess) { error ->
                logout()
                onError.invoke(error)
            })
            return@withContext
        }
        repository.singIn(SingRequest(email, password), getWebStatus({ tokenModel ->
            userService.singIn(UserLogged(email, tokenModel!!.token, password))
            token = tokenModel.token
            onSuccess.invoke(tokenModel)
        }, { error ->
            logout()
            onError.invoke(error)
        }))
    }

    suspend fun singUp(
        request: SingRequest,
        onSuccess: (User) -> Unit,
        onError: (String) -> Unit,
        context: CoroutineContext = Dispatchers.IO,
    ) = withContext(context = context) {
        repository.singUp(request, getWebStatus(onSuccess, onError))
    }

    fun logout() = userService.logout()

}