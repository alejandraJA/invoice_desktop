package com.invoice.contratista.ui.section.part

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.response.CostEntity
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.data.source.web.models.response.event.TaxEntity
import com.invoice.contratista.utils.CUOTA
import com.invoice.contratista.utils.EXENTO
import com.invoice.contratista.utils.MoneyUtils.getTax
import com.invoice.contratista.utils.getDate
import org.koin.core.component.KoinComponent

class PartViewModel : KoinComponent {
    var cost: MutableState<CostEntity?> = mutableStateOf(null)
    val quantity: MutableState<Int> = mutableStateOf(0)
    val discount: MutableState<Double> = mutableStateOf(0.0)
    val subTotal: MutableState<Double> = mutableStateOf(0.0)
    private val subTax: MutableState<List<TaxEntity>> = mutableStateOf(listOf())
    private val sumTax: MutableState<Double> = mutableStateOf(0.0)
    private val restTax: MutableState<Double> = mutableStateOf(0.0)
    val total: MutableState<Double> = mutableStateOf(0.0)
    val totalGain: MutableState<Double> = mutableStateOf(0.0)

    fun onChange(
        inventory: MutableState<ProductInventoryModel?>,
        part: MutableState<PartEntity?>
    ) {
        cost.value = if (inventory.value != null) {
            inventory.value!!.costEntities.sortBy { it.date!!.timestamp.getDate() }
            inventory.value!!.costEntities.last()
        } else null

        quantity.value =
            if (part.value != null) (part.value!!.quantity)
            else (0)

        discount.value =
            if (part.value != null) part.value!!.discount
            else 0.0

        subTotal.value =
            if (part.value != null) (part.value!!.reserved.price.unitPrice * part.value!!.quantity) - discount.value
            else 0.0

        subTax.value =
            if (part.value != null) part.value!!.reserved.product.taxEntities.filter { it.factor != EXENTO }
            else emptyList()

        sumTax.value = subTax.value.sumOf {
            if (!it.withholding)
                if (it.factor == CUOTA) it.rate * quantity.value else subTotal.value.getTax(it.rate)
            else 0.0
        }
        restTax.value = subTax.value.sumOf {
            if (it.withholding)
                if (it.factor == CUOTA) it.rate * quantity.value else subTotal.value.getTax(it.rate)
            else 0.0
        }
        total.value = (subTotal.value + sumTax.value - restTax.value)
        totalGain.value = 0.0
    }

}