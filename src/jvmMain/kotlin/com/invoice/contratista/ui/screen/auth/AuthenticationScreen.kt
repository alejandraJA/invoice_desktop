package com.invoice.contratista.ui.screen.auth

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.invoice.contratista.sys.domain.usecase.LoginComponent
import com.invoice.contratista.theme.ModifierPaddingScreen
import kotlinx.coroutines.launch

@Composable
fun AuthenticationScreen() {
    val scope = rememberCoroutineScope()
    val login = LoginComponent()

    Row {
        Spacer(modifier = Modifier.weight(1f))
        LoginSection(modifier = ModifierPaddingScreen.weight(1f)) { email, password ->
            scope.launch {
                login.login(email, password, {
                    println("success")
                }, {
                    println(it)
                })
            }
        }
        SingUpSection(modifier = ModifierPaddingScreen.weight(1f))
        Spacer(modifier = Modifier.weight(1f))
    }
}

