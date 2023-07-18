package com.invoice.contratista.ui.custom.component.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.invoice.contratista.ui.theme.Alpha
import com.invoice.contratista.ui.theme.ModifierFieldImagesSmall

@Composable
fun TextWithTitle(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
    iconResource: String? = null
) = Column(modifier = modifier) {
    Text(text = title, modifier = Alpha, style = MaterialTheme.typography.bodySmall)
    Row {
        if (iconResource != null) Icon(
            painter = painterResource(iconResource),
            modifier = ModifierFieldImagesSmall,
            contentDescription = ""
        )
        Text(text = text)
    }
}