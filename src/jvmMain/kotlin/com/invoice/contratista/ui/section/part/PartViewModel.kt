package com.invoice.contratista.ui.section.part

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.response.CostEntity
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.event.PartEntity
import com.invoice.contratista.data.source.web.models.response.event.TaxEntity
import com.invoice.contratista.service.ReservedService
import com.invoice.contratista.utils.CUOTA
import com.invoice.contratista.utils.EXENTO
import com.invoice.contratista.utils.MoneyUtils.getTax
import com.invoice.contratista.utils.getDate
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PartViewModel : KoinComponent {

    private val service: ReservedService by inject()

    val quantity: MutableState<Int> = mutableStateOf(0)
    val subTotal: MutableState<Double> = mutableStateOf(0.0)
    val total: MutableState<Double> = mutableStateOf(0.0)
    val totalGain: MutableState<Double> = mutableStateOf(0.0)
    val part: MutableState<PartEntity?> = mutableStateOf(null)
    val gainForUnit: MutableState<Double> = mutableStateOf(0.0)
    val amount: MutableState<Double> = mutableStateOf(0.0)
    private val _discount: MutableState<Double> = mutableStateOf(0.0)
    private var _cost: MutableState<CostEntity?> = mutableStateOf(null)
    private val _subTax: MutableState<List<TaxEntity>> = mutableStateOf(listOf())
    private val _sumTax: MutableState<Double> = mutableStateOf(0.0)
    private val _restTax: MutableState<Double> = mutableStateOf(0.0)

    private val loading: MutableState<Boolean> = mutableStateOf(false)
    private val error: MutableState<String> = mutableStateOf("")

    fun setPart(
        inventory: MutableState<ProductInventoryModel?>,
        part: MutableState<PartEntity?>
    ) {
        this.part.value = part.value
        _cost.value = if (inventory.value != null) {
            inventory.value!!.costEntities.sortBy { it.date }
            inventory.value!!.costEntities.last()
        } else null
        calculate(part)
    }

    suspend fun updateProduct(idProduct: String) = service.updateProduct(
        part.value!!.reserved.id,
        idProduct,
        onSuccess = {
            loading.value = false
            part.value?.reserved = it
        },
        onError = {
            error.value = ""
        })

    fun calculate(part: MutableState<PartEntity?>) {
        this.part.value = part.value
        quantity.value =
            if (this.part.value != null) this.part.value!!.quantity
            else 0

        _discount.value =
            if (this.part.value != null) this.part.value!!.discount
            else 0.0

        subTotal.value =
            if (this.part.value != null)
                (this.part.value!!.reserved.price.unitPrice * this.part.value!!.quantity) - _discount.value
            else 0.0

        _subTax.value =
            if (this.part.value != null) this.part.value!!.reserved.product.taxEntities.filter { it.factor != EXENTO }
            else emptyList()

        _sumTax.value = _subTax.value.sumOf {
            if (!it.withholding)
                if (it.factor == CUOTA) it.rate * quantity.value else subTotal.value.getTax(it.rate)
            else 0.0
        }
        _restTax.value = _subTax.value.sumOf {
            if (it.withholding)
                if (it.factor == CUOTA) it.rate * quantity.value else subTotal.value.getTax(it.rate)
            else 0.0
        }
        total.value = (subTotal.value + _sumTax.value - _restTax.value)
        if (_cost.value != null) {
            gainForUnit.value = this.part.value!!.reserved.price.unitPrice - _cost.value!!.unitCost
            totalGain.value = gainForUnit.value * quantity.value
        }
        amount.value = this.part.value!!.reserved.price.unitPrice * quantity.value
    }

    fun onIncrementQuantity(): () -> Unit = {
        quantity.value++
        if (part.value != null) part.value!!.quantity++
    }

    fun onDecrementQuantity(): () -> Unit = {
        quantity.value--
        if (part.value != null) part.value!!.quantity--
    }

    fun onChangeDiscount(): (String) -> Unit = {
        _discount.value = it.ifEmpty { "0" }.toDouble()
        part.value!!.discount = _discount.value
        subTotal.value =
            ((part.value!!.reserved.price.unitPrice * quantity.value) - it.ifEmpty { "0" }.toDouble())
    }

}