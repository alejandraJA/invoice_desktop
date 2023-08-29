package com.invoice.contratista.ui.section.part

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.invoice.contratista.data.source.web.models.response.ProductInventoryModel
import com.invoice.contratista.data.source.web.models.response.event.Part
import com.invoice.contratista.data.source.web.models.response.event.TaxEntity
import com.invoice.contratista.service.ReservedService
import com.invoice.contratista.utils.CUOTA
import com.invoice.contratista.utils.EXENTO
import com.invoice.contratista.utils.MoneyUtils.getTax
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PartViewModel : KoinComponent {

    private val service: ReservedService by inject()

    val inventory: MutableState<ProductInventoryModel?> = mutableStateOf(null)
    val quantity: MutableState<Int> = mutableStateOf(0)
    val subTotal: MutableState<Double> = mutableStateOf(0.0)
    val total: MutableState<Double> = mutableStateOf(0.0)
    val totalGain: MutableState<Double> = mutableStateOf(0.0)
    val part: MutableState<Part?> = mutableStateOf(null)
    val gainForUnit: MutableState<Double> = mutableStateOf(0.0)
    val amount: MutableState<Double> = mutableStateOf(0.0)
    var cost: MutableState<Double> = mutableStateOf(0.0)
    private val _discount: MutableState<Double> = mutableStateOf(0.0)
    private val _subTax: MutableState<List<TaxEntity>> = mutableStateOf(listOf())
    private val _sumTax: MutableState<Double> = mutableStateOf(0.0)
    private val _restTax: MutableState<Double> = mutableStateOf(0.0)
    private val loading: MutableState<Boolean> = mutableStateOf(false)
    private var _onUpdateReserved: (() -> Unit)? = null
    private val error: MutableState<String> = mutableStateOf("")

    val onError = { e: String ->
        error.value = e
    }


    val selectProduct: MutableState<Boolean> = mutableStateOf(false)

    fun setPart(
        part: MutableState<Part?>
    ) {
        this.part.value = part.value
        if (part.value != null) cost.value = part.value!!.reserved.inventory.product.costEntity.unitCost
        calculate(part)
    }

    suspend fun updateProduct(idProduct: String) = service.updateProduct(
        part.value!!.reserved.id,
        idProduct,
        onSuccess = {
            loading.value = false
            part.value?.reserved = it
            _onUpdateReserved?.invoke()
        }, onError
    )

    fun calculate(part: MutableState<Part?>) {
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
            if (this.part.value != null) this.part.value!!.reserved.inventory.product.taxEntities.filter { it.factor != EXENTO }
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
        gainForUnit.value = this.part.value!!.reserved.price.unitPrice - cost.value
        totalGain.value = (gainForUnit.value * quantity.value) - _discount.value
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

    fun setOnUpdateReserved(onUpdateReserved: () -> Unit) {
        this._onUpdateReserved = onUpdateReserved
    }

    suspend fun deletePart(onSuccess: (Boolean) -> Unit) {
        service.deletePart(part.value!!.reserved.id, onSuccess, onError)
    }

    suspend fun updatePart(onSuccess: (Part) -> Unit) {
        service.updatePart(
            part.value!!.reserved.id,
            quantity.value,
            _discount.value,
            onSuccess,
            onError
        )
    }

}