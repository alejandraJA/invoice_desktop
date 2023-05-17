package com.invoice.contratista.ui.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.invoice.contratista.theme.ModifierPaddingScreen

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun AuthenticationScreen() {
    Row(
        modifier = Modifier.fillMaxHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(modifier = ModifierPaddingScreen.weight(1f)) {
            LoginSection()
        }
        Column(modifier = ModifierPaddingScreen.weight(1f)) {
            SingUpSection()
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}