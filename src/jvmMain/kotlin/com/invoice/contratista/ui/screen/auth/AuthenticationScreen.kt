package com.invoice.contratista.ui.screen.auth

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.invoice.contratista.sys.domain.usecase.LoginComponent
import com.invoice.contratista.theme.ModifierPaddingScreen
import com.invoice.contratista.ui.custom.component.ChargingDialog
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun AuthenticationScreen() {
    val scope = rememberCoroutineScope()
    val login = LoginComponent()
    val openDialog = rememberSaveable { mutableStateOf(false) }

    Row {
        Spacer(modifier = Modifier.weight(1f))
        LoginSection(modifier = ModifierPaddingScreen.weight(1f)) { email, password ->
            openDialog.value = true
            scope.launch {
                login.login(email, password, {
                    //openDialog.value = false
                    println("success")
                }, {
                    println(it)
                })
            }
        }
        SingUpSection(modifier = ModifierPaddingScreen.weight(1f))
        Spacer(modifier = Modifier.weight(1f))
        ChargingDialog(show = openDialog) { openDialog.value = false }
    }
}
