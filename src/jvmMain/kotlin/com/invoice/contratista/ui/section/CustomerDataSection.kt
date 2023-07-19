package com.invoice.contratista.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.invoice.contratista.data.source.web.models.response.event.CustomerEntity
import com.invoice.contratista.ui.custom.component.items.TextWithTitle
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import com.invoice.contratista.utils.*

@Composable
fun CustomerDataSection(customerEntity: CustomerEntity) = Column(
    modifier = ModifierPaddingScreen.fillMaxWidth()
) {
    TextWithTitle(
        title = LEGAL_NAME,
        text = customerEntity.legalName
    )
    Row {
        Column(modifier = Modifier.weight(1f)) {
            TextWithTitle(
                title = TAX_IDENTIFICATION,
                text = customerEntity.taxId,
            )
            TextWithTitle(
                title = FISCAL_REGIME,
                text = customerEntity.taxSystem
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            TextWithTitle(
                title = EMAIL,
                text = customerEntity.email
            )
            TextWithTitle(
                title = PHONE,
                text = customerEntity.phone
            )
        }
    }
    TextWithTitle(
        title = ADDRESS,
        text = customerEntity.addressEntity.getAddress()
    )
}