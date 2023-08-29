package com.invoice.contratista.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.invoice.contratista.ui.model.RailItem
import com.invoice.contratista.ui.section.auth.AuthenticationViewModel
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFill
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val scope = rememberCoroutineScope()
    val authenticationViewModel = AuthenticationViewModel()
    var selectedItem by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = 0)
    val items = listOf(
        RailItem.Home,
        RailItem.Contacts,
        RailItem.Inventory,
        RailItem.Invoices,
    )

    Row(modifier = ModifierFill) {
        NavigationRail {
            items.forEachIndexed { index, _ ->
                NavigationRailItem(
                    icon = {
                        Icon(
                            painter = painterResource("drawables/${items[index].icon}.svg"),
                            contentDescription = items[index].title,
                            modifier = ModifierFieldImages
                        )
                    },
                    label = { Text(items[index].title) },
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        scope.launch {
                            pagerState.scrollToPage(page = index)
                        }
                    }
                )
            }
            Spacer(Modifier.weight(1f))
            NavigationRailItem(
                icon = {
                    Icon(
                        painter = painterResource("drawables/logout.svg"),
                        contentDescription = "logout",
                        modifier = ModifierFieldImages
                    )
                },
                selected = false,
                onClick = { scope.launch { authenticationViewModel.logout() } })
            NavigationRailItem(
                icon = {
                    Icon(
                        painter = painterResource("drawables/exit.svg"),
                        contentDescription = "exit",
                        modifier = ModifierFieldImages
                    )
                },
                selected = false,
                onClick = { scope.launch { exitProcess(0) } })
        }
        HorizontalPager(
            modifier = Modifier.fillMaxHeight().weight(1f),
            state = pagerState,
            pageCount = items.size
        ) { page ->
            items[page].screen()
            Text("") // No se mostraba la ui
        }
    }
}