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
import com.invoice.contratista.sys.di.appModule
import com.invoice.contratista.sys.di.webModule
import com.invoice.contratista.sys.domain.usecase.SingComponent
import com.invoice.contratista.theme.DarkColors
import com.invoice.contratista.theme.LightColors
import com.invoice.contratista.theme.Typography
import com.invoice.contratista.ui.screen.auth.AuthenticationScreen
import com.invoice.contratista.ui.screen.main.MainScreen
import org.koin.core.context.startKoin
import java.util.prefs.Preferences


@ExperimentalFoundationApi
@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@Composable
fun InvoiceApp() {
    val darkTheme by rememberSaveable { mutableStateOf(true) }
    val isLoggedUser = remember { mutableStateOf(SingComponent().isLoggedUser) }
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
    ) {
        Scaffold {
            if (isLoggedUser.value) {
                MainScreen(theme = darkTheme) {
                }
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