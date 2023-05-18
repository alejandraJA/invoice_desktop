package com.invoice.contratista.ui.screen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.invoice.contratista.ui.screen.auth.LoginSection
import com.invoice.contratista.ui.screen.auth.SingUpSection

typealias ComposableFun = @Composable () -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
sealed class TabItem(
    var screenType: Boolean,
) {

    lateinit var onSuccess: () -> Unit
    var screen: ComposableFun = if (screenType) {
        { LoginSection { onSuccess.invoke() } }
    } else {
        { SingUpSection { onSuccess.invoke() } }
    }

    object Login : TabItem(screenType = true)

    object SingUp : TabItem(screenType = false)
}
