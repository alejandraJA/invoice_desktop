package com.invoice.contratista.ui.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.invoice.contratista.ui.custom.component.OnValueChange
import com.invoice.contratista.ui.custom.component.TextField


@ExperimentalMaterial3Api
@Composable
fun LoginSection(
    modifier: Modifier,
    login: (String, String) -> Unit
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    var email: String? = null
    var password: String? = null

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
    val onLogIn = object : () -> Unit {
        override fun invoke() {
            email?.let { password?.let { it1 -> login.invoke(it, it1) } }
            errorPassword.value = ""
            errorEmail.value = ""
        }
    }
    val onLostYourPass = object : () -> Unit {
        override fun invoke() {
            println("Lost yur pass")
        }
    }


    // region ui
    Text(text = "Login now!", style = MaterialTheme.typography.titleLarge)
    TextField(
        hint = "Email",
        placeholder = "Type your email",
        icon = "mail",
        isRequired = true,
        change = onEmailChange,
        externalError = errorEmail
    )
    TextField(
        hint = "Password",
        placeholder = "Type your Password",
        icon = "password",
        isRequired = true,
        visualTransformation = PasswordVisualTransformation(),
        change = onPasswordChange,
        externalError = errorPassword
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
    // endregion
}