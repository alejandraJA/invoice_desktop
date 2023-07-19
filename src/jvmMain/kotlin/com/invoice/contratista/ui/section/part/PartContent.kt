package com.invoice.contratista.ui.section.part

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.CostEntity
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.data.source.web.models.response.event.TaxEntity
import com.invoice.contratista.ui.custom.component.TextFieldModel
import com.invoice.contratista.ui.custom.component.TextField
import com.invoice.contratista.ui.custom.component.items.TextWithTitle
import com.invoice.contratista.ui.theme.*
import com.invoice.contratista.utils.*
import com.invoice.contratista.utils.MoneyUtils.getRate
import com.invoice.contratista.utils.MoneyUtils.getTax
import com.invoice.contratista.utils.MoneyUtils.moneyFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartContent(
    inventory: MutableState<ProductInventoryModel?>,
    part: MutableState<PartEntity?>,
    modifier: Modifier
) = Column(modifier) {
    // region Internal Variables
    val sumTax: MutableState<Double>
    val restTax: MutableState<Double>
    val cost: CostEntity? = if (inventory.value != null) {
        inventory.value!!.costEntities.sortBy { it.date!!.timestamp.getDate() }
        inventory.value!!.costEntities.last()
    } else null
    val quantity = rememberSaveable {
        if (part.value != null) mutableStateOf(part.value!!.quantity)
        else mutableStateOf(0)
    }
    val discount = rememberSaveable {
        if (part.value != null) mutableStateOf(part.value!!.discount)
        else mutableStateOf(0.0)
    }
    val subTotal = rememberSaveable {
        if (part.value != null) mutableStateOf((part.value!!.reserved.price.unitPrice * part.value!!.quantity) - discount.value)
        else mutableStateOf(0.0)
    }
    val subTax: MutableState<List<TaxEntity>> =
        mutableStateOf(if (part.value != null) part.value!!.reserved.product.taxEntities.filter { it.factor != EXENTO }
        else emptyList())
    sumTax = mutableStateOf(subTax.value.sumOf {
        if (!it.withholding)
            if (it.factor == CUOTA) it.rate * quantity.value else subTotal.value.getTax(it.rate)
        else 0.0
    })
    restTax = mutableStateOf(subTax.value.sumOf {
        if (it.withholding)
            if (it.factor == CUOTA) it.rate * quantity.value else subTotal.value.getTax(it.rate)
        else 0.0
    })
    val total = mutableStateOf((subTotal.value + sumTax.value - restTax.value))
    val totalGain = mutableStateOf(0.0)
    // endregion
    Text(text = "$PART ${part.value!!.number}", style = MaterialTheme.typography.titleMedium)
    // region Price and Gain
    ElevatedCard {
        Row(modifier = ModifierCard, verticalAlignment = Alignment.CenterVertically) {
            val unitCost = rememberSaveable { mutableStateOf(0.0) }
            if (cost != null) {
                unitCost.value = cost.unitCost
                totalGain.value = (part.value!!.reserved.price.unitPrice - unitCost.value) * quantity.value
            }
            TextWithTitle(
                title = PRICE,
                text = "$ ${part.value!!.reserved.price.unitPrice.moneyFormat()}",
                modifier = Modifier.padding(end = 4.dp).weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = GAIN_FOR_UNIT,
                text = "$ ${(part.value!!.reserved.price.unitPrice - unitCost.value).moneyFormat()}",
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
                IconButton(onClick = { quantity.value-- }, modifier = ModifierFieldImages) {
                    Icon(
                        painter = painterResource("drawables/remove.svg"),
                        modifier = ModifierFieldImagesSmall,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = quantity.value.toString())
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { quantity.value++ }, modifier = ModifierFieldImages) {
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
                    (part.value!!.reserved.price.unitPrice * quantity.value).moneyFormat()
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
                change = {
                    discount.value = it.ifEmpty { "0" }.toDouble()
                    part.value!!.discount = discount.value
                    subTotal.value =
                        ((part.value!!.reserved.price.unitPrice * quantity.value) - it.ifEmpty { "0" }
                            .toDouble())
                },
                initField = mutableStateOf(part.value!!.discount.toInt().toString()),
                icon = "money"
            )
            Column(modifier = Modifier.weight(1f)) {
                TextField(
                    model = model
                )
            }
            Spacer(modifier = Modifier.width(8.dp).height(1.dp))
            TextWithTitle(
                title = SUB_TOTAL,
                modifier = Modifier.weight(1f),
                doubleMutableState = subTotal,
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
                        Text(text = subTotal.value.moneyFormat())
                    }
                }
            }
            items(count = part.value!!.reserved.product.taxEntities.size) { position ->
                val tax = part.value!!.reserved.product.taxEntities[position]
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
                                    if (tax.factor == CUOTA) tax.rate * quantity.value
                                    else subTotal.value.getTax(tax.rate)
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
                        Text(text = total.value.moneyFormat())
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
                        Text(text = totalGain.value.moneyFormat(), modifier = Alpha)
                    }
                }
            }
        }
    }
    // endregion
}