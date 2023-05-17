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
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null
    val errorPassword = rememberSaveable { mutableStateOf("") }
    val errorEmail = rememberSaveable { mutableStateOf("") }
    val onEmailChange = object : OnValueChange {
        override fun onChange(change: String) {
            email = change
            errorPassword.value = ""
            errorEmail.value = ""
        }
    }
    val onPasswordChange = object : OnValueChange {
        override fun onChange(change: String) {
            password = change
            errorPassword.value = ""
            errorEmail.value = ""
        }
    }
    val onPasswordConfirmChange = object : OnValueChange {
        override fun onChange(change: String) {
            passwordConfirm = change
            errorPassword.value = ""
            errorEmail.value = ""
        }
    }
    val state = remember { mutableStateOf(true) }

    val onSingUp = object : () -> Unit {
        override fun invoke() {
            if (email?.isEmpty() == true || password?.isEmpty() == true || passwordConfirm?.isEmpty() == true)
                return
            if (password != passwordConfirm) {
                errorPassword.value = "Password do not match"
                return
            }
        }
    }

    // region UI
    Text(text = "Create an account", style = MaterialTheme.typography.titleLarge)
    TextField(
        hint = "Email",
        placeholder = "Type your email",
        icon = "mail",
        isRequired = true,
        change = onEmailChange,
        externalError = errorEmail,
    )
    TextField(
        hint = "Password",
        placeholder = "Type your Password",
        icon = "password",
        isRequired = true,
        visualTransformation = PasswordVisualTransformation(),
        change = onPasswordChange,
        externalError = errorPassword,
    )
    TextField(
        hint = "Password",
        placeholder = "Confirm Password",
        icon = "password",
        isRequired = true,
        visualTransformation = PasswordVisualTransformation(),
        change = onPasswordConfirmChange,
        externalError = errorPassword,
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = state.value,
            onCheckedChange = {
                state.value = it
            }
        )
        Text(text = "I agree with privacy policy")
    }
    Button(
        onClick = onSingUp,
        modifier = ModifierFill
    ) {
        Text("Sing Up")
    }
    // endregion

}