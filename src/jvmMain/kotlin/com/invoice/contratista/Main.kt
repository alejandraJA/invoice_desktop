package com.invoice.contratista

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.invoice.contratista.sys.di.appModule
import com.invoice.contratista.sys.di.webModule
import org.koin.core.context.startKoin

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column {
            // TODO: Not implemented
        }
    }
}

fun main() {
    startKoin { modules(appModule(), webModule()) }

    application {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}