package com.invoice.contratista

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.invoice.contratista.sys.di.appModule
import com.invoice.contratista.sys.di.webModule
import com.invoice.contratista.sys.domain.usecase.LoginComponent
import com.invoice.contratista.theme.DarkGreenColorPalette
import com.invoice.contratista.theme.LightGreenColorPalette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.context.startKoin

@Composable
@Preview
fun InvoiceApp() {
    val darkTheme by rememberSaveable { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val error = remember { mutableStateOf("") }
    val success = remember { mutableStateOf(false) }
    MaterialTheme(colors = if (darkTheme) DarkGreenColorPalette else LightGreenColorPalette) {
        Column {
            Button(
                onClick = {
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            LoginComponent({
                                success.value = true
                            }, {
                                success.value = false
                                error.value = it
                            }).login("ale@email.com", "ale.-112233")
                        }
                    }
                }) {
                Text(text = "Login!")
            }

            Icon(
                painter = painterResource("drawables/like.svg"),
                contentDescription = "algo",
                Modifier.alpha(if (success.value) 1f else 0f)
            )
            Icon(
                painter = painterResource("drawables/bug.svg"),
                contentDescription = "algo",
                Modifier.alpha(if (error.value.isNotEmpty()) 1f else 0f)
            )
            Text(text = error.value)
        }
    }
}

fun main() {
    startKoin { modules(appModule(), webModule()) }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Invoice Contratista"
        ) {
            InvoiceApp()
        }
    }
}