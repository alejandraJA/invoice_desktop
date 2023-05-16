package com.invoice.contratista.ui.screen.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.invoice.contratista.theme.ModifierFill
import com.invoice.contratista.ui.custom.component.OnValueChange
import com.invoice.contratista.ui.custom.component.TextField

@ExperimentalMaterial3Api
@Composable
fun SingUpSection(modifier: Modifier) = Column(
    modifier = modifier,
) {
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null
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
    val onPasswordConfirmChange = object : OnValueChange {
        override fun onChange(change: String) {
            passwordConfirm = change
        }
    }
    val onNameChange = object : OnValueChange {
        override fun onChange(change: String) {
            name = change
        }
    }
    val state = remember { mutableStateOf(true) }
    val errorFromApi = rememberSaveable { mutableStateOf("") }
    Text(text = "Create an account", style = MaterialTheme.typography.titleLarge)
    TextField(
        hint = "Name",
        placeholder = "Type your name",
        icon = "business",
        isRequired = true,
        change = onNameChange,
        externalError = errorFromApi,
    )
    TextField(
        hint = "Email",
        placeholder = "Type your email",
        icon = "mail",
        isRequired = true,
        change = onEmailChange,
        externalError = errorFromApi,
    )
    TextField(
        hint = "Password",
        placeholder = "Type your Password",
        icon = "password",
        isRequired = true,
        visualTransformation = PasswordVisualTransformation(),
        change = onPasswordChange,
        externalError = errorFromApi,
    )
    TextField(
        hint = "Password",
        placeholder = "Confirm Password",
        icon = "password",
        isRequired = true,
        visualTransformation = PasswordVisualTransformation(),
        change = onPasswordConfirmChange,
        externalError = errorFromApi,
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
        onClick = {},
        modifier = ModifierFill
    ) {
        Text("Sing Up")
    }

}