package com.invoice.contratista.ui.section.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.response.event.Product
import com.invoice.contratista.ui.theme.Alpha
import com.invoice.contratista.ui.theme.ModifierCard
import com.invoice.contratista.utils.*

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun ProductSection(product: Product) = Column(modifier = ModifierCard) {
    val alpha = Alpha.padding(top = 4.dp)
    val typography = MaterialTheme.typography.bodySmall
    Row {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = PRODUCT_SERVICE, modifier = alpha, style = typography)
            Text(text = product.productBase.description)
            Text(text = UNIT_NAME, modifier = alpha, style = typography)
            Text(text = product.productBase.unitName)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = PRODUCT_KEY, modifier = alpha, style = typography)
            Text(text = product.productBase.productKey)
            Text(text = SKU, modifier = alpha, style = typography)
            Text(text = product.productBase.sku)
        }
    }
    Text(text = OTHERS, modifier = alpha, style = typography)
    Text(text = "- ${if (product.productBase.taxIncluded) TAX_INCLUDED else TAX_NOT_INCLUDED}")
    Text(text = "- ${getTaxability(product.productBase.taxability)}")

    Text(text = TAX, modifier = alpha, style = typography)
    TaxLazy(product.taxEntities)
    Text(text = PRICES, modifier = alpha, style = typography)
    PriceLazy(product.priceEntities)

    ProductInventorySection(product.id)
}

