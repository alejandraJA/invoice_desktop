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
import com.invoice.contratista.ui.custom.component.TextField
import com.invoice.contratista.ui.custom.component.TextFieldModel
import com.invoice.contratista.ui.custom.component.items.TextWithTitle
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
    // region Price and Gain
    ElevatedCard {
        Row(modifier = ModifierCard, verticalAlignment = Alignment.CenterVertically) {
            TextWithTitle(
                title = PRICE,
                text = "$ ${viewModel.part.value!!.reserved.price.unitPrice.moneyFormat()}",
                modifier = Modifier.padding(end = 4.dp).weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = GAIN_FOR_UNIT,
                text = "$ ${viewModel.gainForUnit.value.moneyFormat()}",
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
    // region Discount
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(modifier = Modifier.weight(1f)) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = SUB_TOTAL, style = MaterialTheme.typography.bodySmall)
                    }
                    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource("drawables/money.svg"),
                            contentDescription = "money",
                            modifier = ModifierFieldImages
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = viewModel.subTotal.value.moneyFormat())
                    }
                }
            }
            items(count = viewModel.part.value!!.reserved.product.taxEntities.size) { position ->
                val tax = viewModel.part.value!!.reserved.product.taxEntities[position]
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(modifier = Modifier.weight(1f)) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${tax.type} (${tax.rate.getRate()} %)",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = if (tax.factor == EXENTO) Alpha else Modifier
                        )
                        Text(
                            text = tax.factor,
                            modifier = Alpha.padding(start = 4.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource("drawables/money.svg"),
                            contentDescription = "money",
                            modifier = ModifierFieldImages
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = (
                                    if (tax.factor == CUOTA) tax.rate * viewModel.quantity.value
                                    else viewModel.subTotal.value.getTax(tax.rate)
                                    ).moneyFormat(),
                            modifier = if (tax.factor == EXENTO) Alpha else Modifier
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Divider(modifier = ModifierFill)
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(modifier = Modifier.weight(1f)) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = TOTAL, style = MaterialTheme.typography.bodySmall)
                    }
                    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource("drawables/money.svg"),
                            contentDescription = "money",
                            modifier = ModifierFieldImages
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = viewModel.total.value.moneyFormat())
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(modifier = Modifier.weight(1f)) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = TOTAL_GAIN, style = MaterialTheme.typography.bodySmall, modifier = Alpha)
                    }
                    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource("drawables/money.svg"),
                            contentDescription = "money",
                            modifier = ModifierFieldImages
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = viewModel.totalGain.value.moneyFormat(), modifier = Alpha)
                    }
                }
            }
        }
    }
    // endregion
}