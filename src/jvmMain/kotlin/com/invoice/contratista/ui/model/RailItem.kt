package com.invoice.contratista.ui.model

import com.invoice.contratista.ui.screen.ContactScreen
import com.invoice.contratista.ui.screen.HomeScreen
import com.invoice.contratista.ui.screen.InventoryScreen
import com.invoice.contratista.ui.screen.InvoiceScreen
import com.invoice.contratista.utils.ComposableFun

sealed class RailItem(
    val title: String,
    val icon: String,
    val screen: ComposableFun
) {
    object Home : RailItem(
        title = "Home",
        icon = "home", { HomeScreen() }
    )

    object Contacts : RailItem(
        title = "Contacts",
        icon = "contacts",
        { ContactScreen() })

    object Inventory : RailItem(
        title = "Inventory",
        icon = "inventory",
        { InventoryScreen() }
    )

    object Invoices : RailItem(
        title = "Invoices",
        icon = "receipt_long",
        { InvoiceScreen() }
    )
}