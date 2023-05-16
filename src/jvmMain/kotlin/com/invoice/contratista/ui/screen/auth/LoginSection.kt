package com.invoice.contratista.ui.screen.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.invoice.contratista.ui.custom.component.OnValueChange
import com.invoice.contratista.ui.custom.component.TextField


@ExperimentalMaterial3Api
@Composable
fun LoginSection(modifier: Modifier, errorFromApi: MutableState<String>, login: (String, String) -> Unit) =
    Column(modifier = modifier) {
        var email: String? = null
        var password: String? = null
        val onEmailChange = object : OnValueChange {
            override fun onChange(change: String) {
                email = change
            }
        }
        val onPasswordChange = object : OnValueChange {
            override fun onChange(change: String) {
                password = change
            }
        }

        // region ui
        Text(text = "Login now!", style = MaterialTheme.typography.titleLarge)
        TextField(
            initField = "ale@email.com",
            hint = "Email",
            placeholder = "Type your email",
            icon = "mail",
            isRequired = true,
            change = onEmailChange,
            externalError = errorFromApi
        )
        TextField(
            initField = "ale.-112233",
            hint = "Password",
            placeholder = "Type your Password",
            icon = "password",
            isRequired = true,
            visualTransformation = PasswordVisualTransformation(),
            change = onPasswordChange,
            externalError = errorFromApi
        )
        Row {
            TextButton(
                onClick = {
                    println("Lost yur pass")
                },
            ) {
                Text("Lost your password?")
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    email?.let { password?.let { it1 -> login.invoke(it, it1) } }
                },
            ) {
                Text("Login")
            }
        }
        // endregion
    }