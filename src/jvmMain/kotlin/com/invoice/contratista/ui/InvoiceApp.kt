package com.invoice.contratista.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.invoice.contratista.AppViewModel
import com.invoice.contratista.ui.screen.AuthenticationScreen
import com.invoice.contratista.ui.screen.MainScreen
import com.invoice.contratista.ui.theme.DarkColors
import com.invoice.contratista.ui.theme.LightColors
import com.invoice.contratista.ui.theme.Typography
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@Composable
fun InvoiceApp() {
    val appViewModel = remember { AppViewModel() }
    val scope = rememberCoroutineScope()
    val darkTheme by rememberSaveable { mutableStateOf(true) }

    scope.launch {
        if (appViewModel.isLoggedUser.value) {
            appViewModel.updateToken {
                appViewModel.isLoggedUser.value = false
            }
        }
    }

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
    ) {
        Scaffold {
            Column {
                if (appViewModel.isLoggedUser.value) {
                    if (appViewModel.updateToken.value) MainScreen()
                } else {
                    AuthenticationScreen(onLoggedUser = {
                        appViewModel.isLoggedUser.value = true
                        appViewModel.updateToken.value = true
                    })
                }
            }
        }
    }
}