package com.invoice.contratista.ui.model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.invoice.contratista.ui.section.auth.LoginSection
import com.invoice.contratista.ui.section.auth.SingUpSection
import com.invoice.contratista.utils.ComposableFun

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
sealed class TabItem(
    var screenType: Boolean,
) {

    lateinit var onSuccess: () -> Unit
    var screen: ComposableFun = if (screenType) {
        { LoginSection {
            onSuccess.invoke()
        } }
    } else {
        { SingUpSection { onSuccess.invoke() } }
    }

    object Login : TabItem(screenType = true)

    object SingUp : TabItem(screenType = false)
}