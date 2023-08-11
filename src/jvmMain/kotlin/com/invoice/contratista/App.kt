package com.invoice.contratista

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.invoice.contratista.di.appModule
import com.invoice.contratista.di.webModule
import com.invoice.contratista.ui.InvoiceApp
import com.invoice.contratista.ui.theme.DarkColors
import org.koin.core.context.startKoin

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
fun main() {
    startKoin { modules(appModule(), webModule()) }
    application {
        val windowState = rememberSaveable { WindowState(WindowPlacement.Maximized) }
        val fullscreen = rememberSaveable { mutableStateOf(windowState.placement == WindowPlacement.Maximized) }
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            undecorated = true,
            resizable = !fullscreen.value
        ) {
            MaterialTheme(colorScheme = DarkColors) {
                Scaffold {
                    Column {
                        if (!fullscreen.value) WindowDraggableArea {
                            ChangedStateComponent(windowState, fullscreen)
                        } else ChangedStateComponent(windowState, fullscreen)
//                        Text("X: ${windowState.position.x.value}, Y: ${windowState.position.y.value}")
                        InvoiceApp()
                    }
                }
            }
        }
    }
}

