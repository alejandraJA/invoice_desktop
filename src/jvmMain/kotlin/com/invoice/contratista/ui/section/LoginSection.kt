package com.invoice.contratista.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.invoice.contratista.sys.domain.repository.component.SingComponent
import com.invoice.contratista.ui.custom.component.ErrorDialog
import com.invoice.contratista.ui.custom.component.LoadingDialog
import com.invoice.contratista.ui.custom.component.TextField
import com.invoice.contratista.ui.custom.component.TextFieldModel
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun LoginSection(onLoggedUser: () -> Unit) {
    // region logic
    var email: String? = null
    var password: String? = null

    val errorPassword = rememberSaveable { mutableStateOf("") }
    val errorEmail = rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val sing = SingComponent()
    val loadingDialogState = rememberSaveable { mutableStateOf(false) }
    val errorState = rememberSaveable { mutableStateOf("") }
    val onError = { error: String ->
        loadingDialogState.value = false
        errorState.value = error
    }

    val onSuccessLogin = {
        onLoggedUser.invoke()
        loadingDialogState.value = false
        errorState.value = ""
    }
    val onEmailChange = { change: String ->
        email = change
        errorPassword.value = ""
        errorEmail.value = ""
    }
    val onPasswordChange = { change: String ->
        password = change
        errorPassword.value = ""
        errorEmail.value = ""
    }
    val onLogin = { email: String, password: String ->
        loadingDialogState.value = true
        scope.launch {
            sing.login(email, password, onSuccessLogin, onError)
        }
    }
    val onLogIn = {
        email?.let { password?.let { it1 -> onLogin.invoke(it, it1) } }
        errorPassword.value = ""
        errorEmail.value = ""
    }

    val onLostYourPass = {
        println("Lost yur pass")
    }

    // endregion


    // region ui
    ElevatedCard {
        Column(
            modifier = ModifierPaddingScreen,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Login now!", style = MaterialTheme.typography.titleLarge)
            TextField(
                TextFieldModel(
                    initField = "ale@email.com",
                    hint = "Email",
                    placeholder = "Type your email",
                    icon = "mail",
                    isRequired = true,
                    change = onEmailChange,
                    externalError = errorEmail
                )
            )
            TextField(
                TextFieldModel(
                    initField = "ale.-112233",
                    hint = "Password",
                    placeholder = "Type your Password",
                    icon = "password",
                    isRequired = true,
                    visualTransformation = PasswordVisualTransformation(),
                    change = onPasswordChange,
                    externalError = errorPassword
                )
            )
            Row {
                TextButton(
                    onClick = onLostYourPass,
                ) {
                    Text("Lost your password?")
                }
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = onLogIn,
                ) {
                    Text("Login")
                }
            }
        }
    }
    LoadingDialog(show = loadingDialogState) { loadingDialogState.value = false }
    ErrorDialog(errorState) {
        errorState.value = ""
    }
    // endregion
}