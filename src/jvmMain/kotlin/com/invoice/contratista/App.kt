package com.invoice.contratista

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.invoice.contratista.di.appModule
import com.invoice.contratista.di.webModule
import com.invoice.contratista.service.SingService
import com.invoice.contratista.ui.screen.AuthenticationScreen
import com.invoice.contratista.ui.screen.MainScreen
import com.invoice.contratista.ui.theme.DarkColors
import com.invoice.contratista.ui.theme.LightColors
import com.invoice.contratista.ui.theme.Typography
import kotlinx.coroutines.launch
import org.koin.core.context.startKoin


@ExperimentalFoundationApi
@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@Composable
fun InvoiceApp() {
    val singService = SingService()
    val scope = rememberCoroutineScope()
    val darkTheme by rememberSaveable { mutableStateOf(true) }
    val isLoggedUser = remember { mutableStateOf(singService.isLoggedUser) }
    val updateToken = remember { mutableStateOf(false) }

    scope.launch {
        if (singService.isLoggedUser) {
            singService.updateToken {
                updateToken.value = true
            }
        }
    }

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
    ) {
        Scaffold {
            if (isLoggedUser.value) {
                if (updateToken.value) MainScreen()
            } else {
                AuthenticationScreen(onLoggedUser = {
                    isLoggedUser.value = true
                })
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
fun main() {
    startKoin { modules(appModule(), webModule()) }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Invoice Contratista",
            state = WindowState(placement = WindowPlacement.Maximized),
        ) {
            InvoiceApp()
        }
    }
}