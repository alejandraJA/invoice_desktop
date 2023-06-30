package com.invoice.contratista.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.CustomerEntity
import com.invoice.contratista.ui.theme.Alpha
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import com.invoice.contratista.utils.*

@Composable
fun CustomerDataSection(customerEntity: CustomerEntity) = Column(
    modifier = ModifierPaddingScreen.fillMaxWidth()
) {
    Text(text = LEGAL_NAME, style = MaterialTheme.typography.bodySmall, modifier = Alpha)
    Text(text = customerEntity.legalName, style = MaterialTheme.typography.bodySmall)
    Row {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = TAX_IDENTIFICATION,
                style = MaterialTheme.typography.bodySmall,
                modifier = Alpha.padding(top = 8.dp)
            )
            Text(text = customerEntity.taxId, style = MaterialTheme.typography.bodySmall)
            Text(
                text = FISCAL_REGIME,
                style = MaterialTheme.typography.bodySmall,
                modifier = Alpha.padding(top = 8.dp)
            )
            Text(text = customerEntity.taxSystem, style = MaterialTheme.typography.bodySmall)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = EMAIL, style = MaterialTheme.typography.bodySmall, modifier = Alpha.padding(top = 8.dp))
            Text(text = customerEntity.email, style = MaterialTheme.typography.bodySmall)
            Text(text = PHONE, style = MaterialTheme.typography.bodySmall, modifier = Alpha.padding(top = 8.dp))
            Text(text = customerEntity.phone, style = MaterialTheme.typography.bodySmall)
        }
    }
    Text(text = ADDRESS, style = MaterialTheme.typography.bodySmall, modifier = Alpha.padding(top = 8.dp))
    Text(text = customerEntity.addressEntity.getAddress(), style = MaterialTheme.typography.bodySmall)
}