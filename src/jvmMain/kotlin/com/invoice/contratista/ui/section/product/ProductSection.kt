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
import com.invoice.contratista.ui.custom.component.items.TextWithTitle
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
            TextWithTitle(
                title = PRODUCT_SERVICE,
                text = product.productBase.description,
            )
            TextWithTitle(
                title = UNIT_NAME,
                text = product.productBase.unitName,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            TextWithTitle(
                title = PRODUCT_KEY,
                text = product.productBase.productKey,
            )
            TextWithTitle(
                title = SKU,
                text = product.productBase.sku,
            )
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

