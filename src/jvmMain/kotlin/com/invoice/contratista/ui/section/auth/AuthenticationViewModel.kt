package com.invoice.contratista.ui.section.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.data.source.web.models.response.TokenModel
import com.invoice.contratista.data.source.web.models.response.UserModel
import com.invoice.contratista.service.SingService
import com.invoice.contratista.utils.Constants
import com.invoice.contratista.utils.EMAIL_CANNOT_EQUALS_PASSWORD
import com.invoice.contratista.utils.PASSWORD_NOT_MATCH
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthenticationViewModel : KoinComponent {
    private val singService: SingService by inject()

    lateinit var onLoggedUser: () -> Unit
    lateinit var onLogin: (String, String) -> Unit
    lateinit var auth: Constants.Authentication

    var email: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")
    val passwordConfirm: MutableState<String> = mutableStateOf("")
    val checkState: MutableState<Boolean> = mutableStateOf(true)

    val errorPassword: MutableState<String> = mutableStateOf("")
    val errorEmail: MutableState<String> = mutableStateOf("")
    val loadingDialogState: MutableState<Boolean> = mutableStateOf(false)
    val errorState: MutableState<String> = mutableStateOf("")

    private val onError = { error: String ->
        loadingDialogState.value = false
        errorState.value = error
    }

    private val onSuccessLogin = { _: TokenModel ->
        onSing()
    }

    private val onSuccessSingUp = { _: UserModel ->
        onSing()
    }

    private fun onSing() {
        onLoggedUser.invoke()
        loadingDialogState.value = false
        errorState.value = ""
    }

    val onEmailChange = { change: String ->
        when (auth) {
            Constants.Authentication.SingIn -> {
                email.value = change
                errorPassword.value = ""
                errorEmail.value = ""
            }

            Constants.Authentication.SingUp -> {
                email.value = change
                errorEmail.value =
                    if (email.value == password.value)
                        EMAIL_CANNOT_EQUALS_PASSWORD
                    else ""
            }
        }
    }
    val onPasswordChange = { change: String ->
        when (auth) {
            Constants.Authentication.SingIn -> {
                password.value = change
                errorPassword.value = ""
                errorEmail.value = ""
            }
            Constants.Authentication.SingUp -> {
                password.value = change
                errorEmail.value =
                    if (email.value == password.value)
                        EMAIL_CANNOT_EQUALS_PASSWORD
                    else ""
                errorPassword.value =
                    if (password.value != passwordConfirm.value)
                        PASSWORD_NOT_MATCH
                    else ""
            }
        }
    }

    val onPasswordConfirmChange = { change: String ->
        passwordConfirm.value = change
        errorEmail.value =
            if (email.value == password.value)
                EMAIL_CANNOT_EQUALS_PASSWORD
            else ""
        errorPassword.value =
            if (password.value != passwordConfirm.value)
                PASSWORD_NOT_MATCH
            else ""
    }

    val onLogIn = {
        onLogin.invoke(email.value, password.value)
        errorPassword.value = ""
        errorEmail.value = ""
    }

    suspend fun singUp(request:SingRequest) {
        loadingDialogState.value = true
        singService.singUp(request, onSuccessSingUp, onError)
    }

    suspend fun login(email: String, password: String) {
        loadingDialogState.value = true
        singService.login(email, password, onSuccessLogin, onError)
    }

    fun logout() = singService.logout()

    val onLostYourPass = {
        println("Lost yur pass")
    }
}