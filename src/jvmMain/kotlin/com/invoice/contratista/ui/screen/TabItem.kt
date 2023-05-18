package com.invoice.contratista.ui.screen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.ui.screen.auth.LoginSection
import com.invoice.contratista.ui.screen.auth.SingUpSection

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(
    var icon: String,
    var title: String,
    var screen: ComposableFun,
) {

    @ExperimentalMaterialApi
    @OptIn(ExperimentalMaterial3Api::class)
    object Login :
        TabItem(icon = "", title = "Login", screen = { LoginSection() })

    @ExperimentalMaterialApi
    @OptIn(ExperimentalMaterial3Api::class)
    object SingIn : TabItem(icon = "", title = "Sing Up", screen = { SingUpSection() })
}