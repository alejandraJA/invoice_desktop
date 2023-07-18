package com.invoice.contratista.utils

import java.text.DecimalFormat
import java.text.NumberFormat

object MoneyUtils {
    /**
     * Metodo para darle formato de moneda a un dato tipo [Double]
     */
    fun Double.moneyFormat(): String {
        val format: NumberFormat = DecimalFormat("###,###.##")
        return format.format(this)
    }

    /**
     * Metodo para obtener el monto del impuesto que se aplicará al
     * subtotal de un producto.
     *
     * **Nota** Este metodo debe ser aplicado en el subtotal del producto.
     * @param rate Tasa del impuesto.
     */
    fun Double.getTax(rate: Double) = this * rate
}