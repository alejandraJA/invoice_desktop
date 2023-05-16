package com.invoice.contratista

import androidx.compose.desktop.ui.tooling.preview.Preview
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
import com.invoice.contratista.sys.domain.usecase.LoginComponent
import com.invoice.contratista.theme.*
import com.invoice.contratista.ui.screen.auth.AuthenticationScreen
import com.invoice.contratista.ui.screen.main.MainScreen
import org.koin.core.context.startKoin

@ExperimentalMaterial3Api
@Composable
@Preview
fun InvoiceApp() {
    val darkTheme by rememberSaveable { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val error = remember { mutableStateOf("") }
    val success = remember { mutableStateOf(false) }
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
    ) {
//        LoginComponent().login("ale@email.com", "ale.-112233", {}, {})
        Scaffold {
            if (LoginComponent().isLoggedUser) {
                MainScreen(theme = darkTheme) {
                    // TODO: Not yet implemented
                }
            } else {
                AuthenticationScreen()
            }
        }
    }
}

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