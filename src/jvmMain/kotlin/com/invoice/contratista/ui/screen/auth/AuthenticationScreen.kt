package com.invoice.contratista.ui.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.invoice.contratista.theme.ModifierPaddingScreen
import com.invoice.contratista.ui.custom.component.OnValueChange
import com.invoice.contratista.ui.custom.component.TextField

@Composable
fun AuthenticationScreen() {
//    val scope = rememberCoroutineScope()
//    val login = LoginComponent()
    var email: String? = null
    var password: String? = null
    var name: String? = null
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

    val onNameChange = object : OnValueChange {
        override fun onChange(change: String) {
            name = change
        }
    }

    Row {
        Column(modifier = ModifierPaddingScreen.weight(1f)) {
            Text(text = "Login now!", style = MaterialTheme.typography.titleLarge)
            SetLogin(onEmailChange, onPasswordChange)
            Row {
                TextButton(onClick = {},
                ) {
                    Text("Lost your password?")
                }
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = {},
                ) {
                    Text("Login")
                }
            }
        }
        Column(modifier = ModifierPaddingScreen.weight(1f)) {
            Text(text = "Create an account", style = MaterialTheme.typography.titleLarge)
            TextField(
                hint = "Name",
                placeholder = "Type your name",
                icon = "business",
                isRequired = true,
                change = onNameChange,
            )
            SetLogin(onEmailChange, onPasswordChange)
            Row {
                TextButton(onClick = {},
                ) {
                    Text("Lost your password?")
                }
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = {},
                ) {
                    Text("Login")
                }
            }
        }
    }
}

@Composable
fun SetLogin(onEmailChange: OnValueChange, onPasswordChange: OnValueChange) {
    Column {
        TextField(
            hint = "Email",
            placeholder = "Type your email",
            icon = "mail",
            isRequired = true,
            change = onEmailChange,
        )
        TextField(
            hint = "Password",
            placeholder = "Type your Password",
            icon = "password",
            isRequired = true,
            visualTransformation = PasswordVisualTransformation(),
            change = onPasswordChange,
        )
    }
}
