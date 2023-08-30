package com.invoice.contratista.ui.section.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.ProductInventory
import com.invoice.contratista.ui.custom.component.TextWithTitle
import com.invoice.contratista.ui.theme.Alpha
import com.invoice.contratista.ui.theme.ModifierCard
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.utils.*

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun ProductSection(inventory: ProductInventory) = Column(modifier = ModifierCard) {
    val alpha = Alpha.padding(top = 4.dp)
    val product = inventory.product
    val typography = MaterialTheme.typography.bodySmall
    Row {
        Column(modifier = Modifier.weight(1f)) {
            TextWithTitle(
                title = PRODUCT_SERVICE,
                text = product.productBase.description,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextWithTitle(
                    title = UNIT_NAME,
                    text = product.productBase.unitName,
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(
                        "drawables/${
                            if (inventory.product.productBase.type) "inventory"
                            else "engineering"
                        }.svg"
                    ),
                    contentDescription = TYPE,
                    modifier = ModifierFieldImages.padding(end = 4.dp)
                )
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            TextWithTitle(
                title = PRODUCT_KEY,
                text = product.productBase.productKey
            )
            TextWithTitle(
                title = SKU,
                text = product.productBase.sku
            )
        }
    }
    Text(text = OTHERS, modifier = alpha, style = typography)
    Text(text = "- ${if (product.productBase.taxIncluded) TAX_INCLUDED else TAX_NOT_INCLUDED}")
    Text(text = "- ${getTaxability(product.productBase.taxability)}")
    Text(text = TAX, modifier = alpha, style = typography)
    TaxLazy(product.taxEntities)
    Text(text = PRICES, modifier = alpha, style = typography)
    PriceLazy(product.productBase.priceEntities)

    ProductInventorySection(inventory)
}

