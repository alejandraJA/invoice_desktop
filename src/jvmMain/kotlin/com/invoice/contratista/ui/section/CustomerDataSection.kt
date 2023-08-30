package com.invoice.contratista.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.invoice.contratista.data.source.web.models.Customer
import com.invoice.contratista.ui.custom.component.TextWithTitle
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import com.invoice.contratista.utils.*

@Composable
fun CustomerDataSection(
    customerEntity: Customer,
    resume: Boolean = true
) = Column(modifier = ModifierPaddingScreen.fillMaxWidth()) {
    TextWithTitle(
        title = LEGAL_NAME,
        text = customerEntity.legalName
    )
    Row {
        Column(modifier = Modifier.weight(1f)) {
            TextWithTitle(
                title = PHONE,
                text = customerEntity.phone
            )
            if (resume) TextWithTitle(
                title = TAX_IDENTIFICATION,
                text = customerEntity.taxId,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            TextWithTitle(
                title = EMAIL,
                text = customerEntity.email
            )
            if (resume) TextWithTitle(
                title = FISCAL_REGIME,
                text = customerEntity.taxSystem
            )
        }
    }
    if (resume) TextWithTitle(
        title = ADDRESS,
        text = customerEntity.addressEntity.getAddress()
    )
}