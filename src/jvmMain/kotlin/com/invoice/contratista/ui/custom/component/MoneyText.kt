package com.invoice.contratista.ui.custom.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.ui.theme.Alpha
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFieldImagesSmall
import com.invoice.contratista.utils.EXENTO
import com.invoice.contratista.utils.MoneyUtils.moneyFormat

@Composable
fun MoneyText(
    indicator: String = "",
    money: Double,
    factor: String = "",
    gain: Boolean = false,
    withholding: Boolean = false,
    modifierRow: Modifier = Modifier
) = Row(verticalAlignment = Alignment.CenterVertically, modifier = modifierRow) {
    val modifier =
        if (factor == EXENTO || gain) ModifierFieldImages.alpha(0.5f)
        else ModifierFieldImages
    if (factor.isNotEmpty() || indicator.isNotEmpty() || factor.isNotEmpty())
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            if (factor.isNotEmpty())
                Icon(
                    painter = painterResource(
                        "drawables/${
                            if (withholding) "remove" else "add"
                        }.svg"
                    ),
                    contentDescription = "withholding",
                    modifier = ModifierFieldImagesSmall.alpha(0.5f)
                )
            Spacer(modifier = Modifier.weight(1f))
            if (indicator.isNotEmpty()) Text(
                text = indicator,
                style = MaterialTheme.typography.bodySmall,
                modifier = (if (factor == EXENTO || gain) Alpha else Modifier)
            )
            if (factor.isNotEmpty()) Text(
                text = factor,
                modifier = Alpha.padding(start = 4.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource("drawables/money.svg"),
            contentDescription = "money",
            modifier = modifier
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = money.moneyFormat(),
            modifier = if (factor == EXENTO || gain) Alpha else Modifier
        )
    }
}