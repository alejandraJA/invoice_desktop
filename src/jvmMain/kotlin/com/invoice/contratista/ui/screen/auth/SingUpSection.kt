package com.invoice.contratista.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.theme.ModifierFill
import com.invoice.contratista.ui.custom.component.OnValueChange
import com.invoice.contratista.ui.custom.component.TextField

@ExperimentalMaterial3Api
@Composable
fun SingUpSection(
    modifier: Modifier,
    singUp: (SingRequest) -> Unit
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordConfirm = rememberSaveable { mutableStateOf("") }
    val checkState = remember { mutableStateOf(true) }
    val errorPassword = rememberSaveable { mutableStateOf("") }
    val errorEmail = rememberSaveable { mutableStateOf("") }
    val onEmailChange = object : OnValueChange {
        override fun onChange(change: String) {
            email.value = change
            errorEmail.value =
                if (email.value == password.value)
                    "Email and password cannot be the same"
                else ""
        }
    }
    val onPasswordChange = object : OnValueChange {
        override fun onChange(change: String) {
            password.value = change
            errorEmail.value =
                if (email.value == password.value)
                    "Email and password cannot be the same"
                else ""
            errorPassword.value =
                if (password.value != passwordConfirm.value)
                    "Password do not match"
                else
                    ""
        }
    }
    val onPasswordConfirmChange = object : OnValueChange {
        override fun onChange(change: String) {
            passwordConfirm.value = change
            errorEmail.value =
                if (email.value == password.value)
                    "Email and password cannot be the same"
                else ""
            errorPassword.value =
                if (password.value != passwordConfirm.value)
                    "Password do not match"
                else
                    ""
        }
    }

    val onSingUp = object : () -> Unit {
        override fun invoke() {
            singUp.invoke(SingRequest(email.value, password.value))
        }
    }

    // region UI
    Text(text = "Create an account", style = MaterialTheme.typography.titleLarge)
    // region Field Email
    TextField(
        hint = "Email",
        placeholder = "Type your email",
        icon = "mail",
        isRequired = true,
        change = onEmailChange,
        externalError = errorEmail,
    )
    // endregion
    // region Field Password
    TextField(
        hint = "Password",
        placeholder = "Type your Password",
        icon = "password",
        isRequired = true,
        visualTransformation = PasswordVisualTransformation(),
        change = onPasswordChange,
        externalError = errorPassword,
    )
    // endregion
    // region Field Confirm Password
    TextField(
        hint = "Password",
        placeholder = "Confirm Password",
        icon = "password",
        isRequired = true,
        visualTransformation = PasswordVisualTransformation(),
        change = onPasswordConfirmChange,
        externalError = errorPassword,
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