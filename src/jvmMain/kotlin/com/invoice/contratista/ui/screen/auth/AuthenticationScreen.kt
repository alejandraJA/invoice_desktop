package com.invoice.contratista.ui.screen.auth

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.sys.domain.usecase.SingComponent
import com.invoice.contratista.theme.ModifierPaddingScreen
import com.invoice.contratista.ui.custom.component.LoadingDialog
import com.invoice.contratista.ui.custom.component.ErrorDialog
import kotlinx.coroutines.launch
import java.lang.Error

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun AuthenticationScreen() {
    val scope = rememberCoroutineScope()
    val sing = SingComponent()
    val loadingDialogState = rememberSaveable { mutableStateOf(false) }
    val errorState = rememberSaveable { mutableStateOf("") }
    val onError = object : (String) -> Unit {
        override fun invoke(error: String) {
            loadingDialogState.value = false
            errorState.value = error
        }
    }
    val onSuccessLogin = object : () -> Unit {
        override fun invoke() {
            loadingDialogState.value = false
            errorState.value = ""
        }
    }
    val onLogin = object : (String, String) -> Unit {
        override fun invoke(email: String, password: String) {
            loadingDialogState.value = true
            scope.launch {
                sing.login(email, password, onSuccessLogin, onError)
            }
        }
    }
    val onSingUp = object : (SingRequest) -> Unit {
        override fun invoke(request: SingRequest) {
            loadingDialogState.value = true
            scope.launch {
                sing.singUp(request = request, {
                    scope.launch {
                        sing.login(
                            email = request.username!!,
                            password = request.password!!,
                            onSuccess = onSuccessLogin,
                            onError = onError
                        )
                    }
                }, onError)
            }
        }
    }

    Row {
        LoadingDialog(show = loadingDialogState) { loadingDialogState.value = false }
        ErrorDialog(errorState) {
            errorState.value = ""
        }
        Spacer(modifier = Modifier.weight(1f))
        LoginSection(modifier = ModifierPaddingScreen.weight(1f), onLogin)
        SingUpSection(modifier = ModifierPaddingScreen.weight(1f), onSingUp)
        Spacer(modifier = Modifier.weight(1f))
    }
}