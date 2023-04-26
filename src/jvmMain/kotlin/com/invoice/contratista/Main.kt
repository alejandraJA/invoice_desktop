package com.invoice.contratista

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import com.invoice.contratista.sys.component.LocalComponent
import com.invoice.contratista.sys.di.appModule
import com.invoice.contratista.sys.di.webModule
import com.invoice.contratista.theme.MyApplicationTheme

@Composable
@Preview
fun App(names: List<String>) {

    MyApplicationTheme(darkTheme = true) {
        Column {
            names.forEach {
                Text(it)
            }
        }
    }
}

fun main() {
    startKoin {
        modules(appModule(), webModule())
    }
    val local = LocalComponent()

    application {
        Window(onCloseRequest = ::exitApplication) {
            App(listOf("Hola", "mundo", "kotlin"))
        }
    }
}
