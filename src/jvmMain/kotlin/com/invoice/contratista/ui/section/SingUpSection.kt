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
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.ui.custom.component.ErrorDialog
import com.invoice.contratista.ui.custom.component.LoadingDialog
import com.invoice.contratista.ui.custom.component.TextField
import com.invoice.contratista.ui.custom.component.TextFieldModel
import com.invoice.contratista.ui.section.auth.AuthenticationViewModel
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import com.invoice.contratista.utils.Constants
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun SingUpSection(onLoggedUser: () -> Unit) {
    // region ViewModel
    val viewModel = remember { AuthenticationViewModel() }
    val scope = rememberCoroutineScope()
    viewModel.onLoggedUser = onLoggedUser
    viewModel.auth = Constants.Authentication.SingUp
    val singUp = { request: SingRequest ->
        scope.launch {
            viewModel.singUp(request)
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
                    hint = "Email",
                    change = viewModel.onEmailChange,
                    placeholder = "Type your email",
                    initField = mutableStateOf("email2@email.com"),
                    icon = "mail",
                    isRequired = true,
                    externalError = viewModel.errorEmail,
                )
            )
            // endregion
            // region Field Password
            TextField(
                TextFieldModel(
                    hint = "Password",
                    change = viewModel.onPasswordChange,
                    placeholder = "Type your Password",
                    initField = mutableStateOf("ale.-112233"),
                    icon = "password",
                    isRequired = true,
                    visualTransformation = PasswordVisualTransformation(),
                    externalError = viewModel.errorPassword,
                )
            )
            // endregion
            // region Field Confirm Password
            TextField(
                TextFieldModel(
                    hint = "Password",
                    change = viewModel.onPasswordConfirmChange,
                    placeholder = "Confirm Password",
                    initField = mutableStateOf("ale.-112233"),
                    icon = "password",
                    isRequired = true,
                    visualTransformation = PasswordVisualTransformation(),
                    externalError = viewModel.errorPassword,
                )
            )
            // endregion
            // region Check terms
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = viewModel.checkState.value,
                    onCheckedChange = {
                        viewModel.checkState.value = it
                    }
                )
                Text(text = "I agree with privacy policy")
            }
            // endregion
            // region Button SingUp
            Button(
                onClick = { singUp.invoke(SingRequest(viewModel.email.value, viewModel.password.value)) },
                modifier = ModifierFill,
                enabled = viewModel.checkState.value && (viewModel.email.value.isNotEmpty()
                        && viewModel.password.value.isNotEmpty()
                        && viewModel.passwordConfirm.value.isNotEmpty())
                        && (viewModel.password.value == viewModel.passwordConfirm.value
                        && viewModel.email.value != viewModel.password.value)
            ) {
                Text("Sing Up")
            }
            // endregion
            // endregion
        }
    }
    LoadingDialog(show = viewModel.loadingDialogState) { viewModel.loadingDialogState.value = false }
    ErrorDialog(viewModel.errorState) {
        viewModel.errorState.value = ""
    }
}
