package com.invoice.contratista.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.sys.domain.repository.component.SingComponent
import com.invoice.contratista.ui.custom.component.*
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import com.invoice.contratista.utils.EMAIL_CANNOT_EQUALS_PASSWORD
import com.invoice.contratista.utils.PASSWORD_NOT_MATCH
import com.invoice.contratista.ui.custom.component.ErrorDialog
import com.invoice.contratista.ui.custom.component.LoadingDialog
import com.invoice.contratista.ui.custom.component.TextField
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
        email.value = change
        errorEmail.value =
            if (email.value == password.value)
                EMAIL_CANNOT_EQUALS_PASSWORD
            else ""
    }

    val onPasswordChange = { change: String ->
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

    val onPasswordConfirmChange = { change: String ->
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

    val singUp = { request: SingRequest ->
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
                onClick = { singUp.invoke(SingRequest(email.value, password.value)) },
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
