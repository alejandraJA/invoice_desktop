package com.invoice.contratista.ui.section.part

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.ui.custom.component.MoneyText
import com.invoice.contratista.ui.custom.component.TextField
import com.invoice.contratista.ui.custom.component.TextFieldModel
import com.invoice.contratista.ui.custom.component.TextWithTitle
import com.invoice.contratista.ui.theme.*
import com.invoice.contratista.utils.*
import com.invoice.contratista.utils.MoneyUtils.getRate
import com.invoice.contratista.utils.MoneyUtils.getTax
import com.invoice.contratista.utils.MoneyUtils.moneyFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartContent(
    viewModel: PartViewModel,
    modifier: Modifier
) = Column(modifier) {
    Text(text = "$PART ${viewModel.part.value!!.number}", style = MaterialTheme.typography.titleMedium)

    // region Product Select
    ElevatedCard(onClick = {

    }) {
        Row(modifier = ModifierCard, verticalAlignment = Alignment.CenterVertically) {
            TextWithTitle(
                title = PRODUCT_SERVICE,
                text = viewModel.part.value!!.reserved.inventory.product.name,
                modifier = Modifier.padding(end = 4.dp).weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = UNIT_NAME,
                text = viewModel.part.value!!.reserved.inventory.product.productBase.unitName,
                modifier = Modifier.padding(start = 4.dp).weight(1f),
            )
        }
    }
    // endregion
    // region Price and Gain
    ElevatedCard(modifier = Modifier.padding(top = 8.dp)) {
        Row(modifier = ModifierCard, verticalAlignment = Alignment.CenterVertically) {
            TextWithTitle(
                title = COST,
                format = Constants.Format.Money,
                doubleMutableState = mutableStateOf(viewModel.cost.value),
                modifier = Modifier.padding(end = 4.dp).weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = PRICE,
                format = Constants.Format.Money,
                doubleMutableState = mutableStateOf(viewModel.part.value!!.reserved.price.unitPrice),
                modifier = Modifier.padding(start = 4.dp, end = 4.dp).weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = GAIN_FOR_UNIT,
                format = Constants.Format.Money,
                doubleMutableState = mutableStateOf(viewModel.gainForUnit.value),
                modifier = Modifier.padding(start = 4.dp).weight(1f),
            )
        }
    }
    // endregion
    // region Quantity and Amount
    Text(text = QUANTITY, modifier = Alpha.padding(top = 8.dp), style = MaterialTheme.typography.bodySmall)
    ElevatedCard {
        Row(
            modifier = ModifierCard,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = ModifierCard.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = viewModel.onDecrementQuantity(), modifier = ModifierFieldImages) {
                    Icon(
                        painter = painterResource("drawables/remove.svg"),
                        modifier = ModifierFieldImagesSmall,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = viewModel.quantity.value.toString())
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = viewModel.onIncrementQuantity(), modifier = ModifierFieldImages) {
                    Icon(
                        painter = painterResource("drawables/add.svg"),
                        modifier = ModifierFieldImagesSmall,
                        contentDescription = ""
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = AMOUNT,
                text = "$ ${
                    viewModel.amount.value.moneyFormat()
                }",
                modifier = Modifier.weight(1f),
            )
        }
    }
    //endregion
    // region Discount and Subtotal
    Text(text = SUB_TOTAL, modifier = Alpha.padding(top = 8.dp), style = MaterialTheme.typography.bodySmall)
    ElevatedCard {
        Row(modifier = ModifierCard, verticalAlignment = Alignment.CenterVertically) {
            val model = TextFieldModel(
                hint = DISCOUNT,
                change = viewModel.onChangeDiscount(),
                initField = mutableStateOf(viewModel.part.value!!.discount.toInt().toString()),
                icon = "money"
            )
            Column(modifier = Modifier.weight(1f)) { TextField(model = model) }
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = SUB_TOTAL,
                modifier = Modifier.weight(1f),
                doubleMutableState = viewModel.subTotal,
                format = Constants.Format.Money
            )
        }
    }
    // endregion
    // region IVA and Totals
    Text(text = TOTALS, modifier = Alpha.padding(top = 8.dp), style = MaterialTheme.typography.bodySmall)
    ElevatedCard {
        LazyColumn(modifier = ModifierCard) {
            item {
                MoneyText(
                    indicator = SUB_TOTAL,
                    money = viewModel.subTotal.value,
                )
            }
            items(count = viewModel.part.value!!.reserved.inventory.product.taxEntities.size) { position ->
                val tax = viewModel.part.value!!.reserved.inventory.product.taxEntities[position]
                MoneyText(
                    indicator = "${tax.type} (${tax.rate.getRate()} %)",
                    money = if (tax.factor == CUOTA) tax.rate * viewModel.quantity.value
                    else viewModel.subTotal.value.getTax(tax.rate),
                    factor = tax.factor,
                    withholding = tax.withholding
                )
            }
            item {
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Divider(modifier = ModifierFill)
                Spacer(modifier = Modifier.padding(top = 4.dp))
                MoneyText(
                    indicator = TOTAL,
                    money = viewModel.total.value,
                )
                MoneyText(
                    indicator = TOTAL_GAIN,
                    money = viewModel.totalGain.value,
                    gain = true
                )
            }
        }
    }
    // endregion
}