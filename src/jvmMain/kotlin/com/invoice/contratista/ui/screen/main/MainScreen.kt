package com.invoice.contratista.ui.screen.main

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.invoice.contratista.sys.domain.usecase.SingComponent

@Composable
fun MainScreen(theme: Boolean, changeTheme: () -> Unit) {
    Button({
        SingComponent().logout()
    }) {
        Text("click me")
    }
}