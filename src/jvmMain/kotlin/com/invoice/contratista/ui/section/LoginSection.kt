package com.invoice.contratista.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.invoice.contratista.ui.custom.component.ErrorDialog
import com.invoice.contratista.ui.custom.component.LoadingDialog
import com.invoice.contratista.ui.custom.component.TextField
import com.invoice.contratista.ui.custom.component.TextFieldModel
import com.invoice.contratista.ui.section.auth.AuthenticationViewModel
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import com.invoice.contratista.utils.Constants
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun LoginSection(onLoggedUser: () -> Unit) {
    // region ViewModel
    val viewModel = remember { AuthenticationViewModel() }
    val scope = rememberCoroutineScope()
    viewModel.auth = Constants.Authentication.SingIn
    viewModel.onLoggedUser = onLoggedUser
    viewModel.onLogin = { email: String, password: String ->
        scope.launch {
            viewModel.login(email, password)
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
            Text(text = "Login now!", style = MaterialTheme.typography.titleLarge)
            TextField(
                TextFieldModel(
                    hint = "Email",
                    change = viewModel.onEmailChange,
                    placeholder = "Type your email",
                    initField = mutableStateOf("ale@email.com"),
                    icon = "mail",
                    isRequired = true,
                    externalError = viewModel.errorEmail
                )
            )
            TextField(
                TextFieldModel(
                    hint = "Password",
                    change = viewModel.onPasswordChange,
                    placeholder = "Type your Password",
                    initField = mutableStateOf("ale@email.com"),
                    icon = "password",
                    isRequired = true,
                    visualTransformation = PasswordVisualTransformation(),
                    externalError = viewModel.errorPassword
                )
            )
            Row {
                TextButton(
                    onClick = viewModel.onLostYourPass,
                ) {
                    Text("Lost your password?")
                }
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = viewModel.onLogIn,
                ) {
                    Text("Login")
                }
            }
        }
    }
    LoadingDialog(show = viewModel.loadingDialogState) { viewModel.loadingDialogState.value = false }
    ErrorDialog(viewModel.errorState) {
        viewModel.errorState.value = ""
    }
    // endregion
}