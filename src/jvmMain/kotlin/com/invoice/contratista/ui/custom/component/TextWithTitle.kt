package com.invoice.contratista.ui.custom.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.invoice.contratista.ui.theme.Alpha
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFieldImagesSmall
import com.invoice.contratista.utils.Constants
import com.invoice.contratista.utils.MONEY
import com.invoice.contratista.utils.MoneyUtils.moneyFormat

@Composable
fun TextWithTitle(
    title: String,
    text: String? = null,
    modifier: Modifier = Modifier,
    iconResource: String? = null,
    format: Constants.Format = Constants.Format.Text,
    doubleMutableState: MutableState<Double>? = null,
) = Column(modifier = modifier) {
    Text(text = title, modifier = Alpha, style = MaterialTheme.typography.bodySmall)
    Row(verticalAlignment = Alignment.CenterVertically) {
        val textFormat: String =
            if (doubleMutableState != null) {
                when (format) {
                    Constants.Format.Text -> doubleMutableState.value.toString()
                    Constants.Format.Money -> doubleMutableState.value.moneyFormat()
                }
            } else {
                when (format) {
                    Constants.Format.Text -> text!!
                    Constants.Format.Money -> text!!.toDouble().moneyFormat()
                }
            }
        if (iconResource != null || format == Constants.Format.Money) Icon(
            painter = painterResource(if (format == Constants.Format.Money) "drawables/money.svg" else iconResource!!),
            modifier = if (format == Constants.Format.Money) ModifierFieldImages else ModifierFieldImagesSmall,
            contentDescription = if (format == Constants.Format.Money) MONEY else iconResource!!
        )
        if (format == Constants.Format.Money)
            Spacer(modifier = Modifier.weight(1f))
        Text(text = textFormat)
    }
}