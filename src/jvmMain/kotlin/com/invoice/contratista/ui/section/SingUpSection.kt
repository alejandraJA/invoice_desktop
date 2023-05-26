package com.invoice.contratista.ui.section

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.sys.domain.usecase.SingComponent
import com.invoice.contratista.ui.custom.component.*
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import com.invoice.contratista.utils.EMAIL_CANNOT_EQUALS_PASSWORD
import com.invoice.contratista.utils.PASSWORD_NOT_MATCH
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun SingUpSection(onLoggedUser: () -> Unit) {
    // region logic
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordConfirm = rememberSaveable { mutableStateOf("") }
    val checkState = remember { mutableStateOf(true) }
    val errorPassword = rememberSaveable { mutableStateOf("") }
    val errorEmail = rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val sing = SingComponent()
    val loadingDialogState = rememberSaveable { mutableStateOf(false) }
    val errorState = rememberSaveable { mutableStateOf("") }
    val onError: (String) -> Unit = {
        loadingDialogState.value = false
        errorState.value = it
    }

    val onSuccessLogin: () -> Unit = {
        onLoggedUser.invoke()
        loadingDialogState.value = false
        errorState.value = ""
    }

    val onEmailChange = object : OnValueChange {
        override fun onChange(change: String) {
            email.value = change
            errorEmail.value =
                if (email.value == password.value)
                    EMAIL_CANNOT_EQUALS_PASSWORD
                else ""
        }
    }
    val onPasswordChange = object : OnValueChange {
        override fun onChange(change: String) {
            password.value = change
            errorEmail.value =
                if (email.value == password.value)
                    EMAIL_CANNOT_EQUALS_PASSWORD
                else ""
            errorPassword.value =
                if (password.value != passwordConfirm.value)
                    PASSWORD_NOT_MATCH
                else
                    ""
        }
    }
    val onPasswordConfirmChange = object : OnValueChange {
        override fun onChange(change: String) {
            passwordConfirm.value = change
            errorEmail.value =
                if (email.value == password.value)
                    EMAIL_CANNOT_EQUALS_PASSWORD
                else ""
            errorPassword.value =
                if (password.value != passwordConfirm.value)
                    PASSWORD_NOT_MATCH
                else
                    ""
        }
    }

    val singUp: (SingRequest) -> Unit = { request ->
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

    val onSingUp: () -> Unit = {
        singUp.invoke(SingRequest(email.value, password.value))
    }
    // endregion

    // region UI
    ElevatedCard {
        Column(
            modifier = ModifierPaddingScreen,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Create an account", style = MaterialTheme.typography.titleLarge)
            // region Field Email
            TextField(
                TextFieldModel(
                    initField = "email2@email.com",
                    hint = "Email",
                    placeholder = "Type your email",
                    icon = "mail",
                    isRequired = true,
                    change = onEmailChange,
                    externalError = errorEmail,
                )
            )
            // endregion
            // region Field Password
            TextField(
                TextFieldModel(
                    initField = "ale.-112233",
                    hint = "Password",
                    placeholder = "Type your Password",
                    icon = "password",
                    isRequired = true,
                    visualTransformation = PasswordVisualTransformation(),
                    change = onPasswordChange,
                    externalError = errorPassword,
                )
            )
            // endregion
            // region Field Confirm Password
            TextField(
                TextFieldModel(
                    initField = "ale.-112233",
                    hint = "Password",
                    placeholder = "Confirm Password",
                    icon = "password",
                    isRequired = true,
                    visualTransformation = PasswordVisualTransformation(),
                    change = onPasswordConfirmChange,
                    externalError = errorPassword,
                )
            )
            // endregion
            // region Check terms
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkState.value,
                    onCheckedChange = {
                        checkState.value = it
                    }
                )
                Text(text = "I agree with privacy policy")
            }
            // endregion
            // region Button SingUp
            Button(
                onClick = onSingUp,
                modifier = ModifierFill,
                enabled = checkState.value && (email.value.isNotEmpty()
                        && password.value.isNotEmpty() && passwordConfirm.value.isNotEmpty())
                        && (password.value == passwordConfirm.value
                        && email.value != password.value)
            ) {
                Text("Sing Up")
            }
            // endregion
            // endregion
        }
    }
    LoadingDialog(show = loadingDialogState) { loadingDialogState.value = false }
    ErrorDialog(errorState) {
        errorState.value = ""
    }
}
