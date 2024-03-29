package com.invoice.contratista.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.invoice.contratista.ui.model.TabItem
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun AuthenticationScreen(onLoggedUser: () -> Unit) = Column(
    modifier = Modifier.fillMaxHeight(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val scope = rememberCoroutineScope()
    val tabs = listOf(
        TabItem.Login,
        TabItem.SingUp,
    )
    val pagerState = rememberPagerState(initialPage = 0)

    // region screen
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        HorizontalPager(modifier = Modifier.weight(1f), state = pagerState, pageCount = tabs.size) { page ->
            tabs[page].onSuccess = onLoggedUser
            tabs[page].screen()
            Text("")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
    TextButton(onClick = {
        scope.launch {
            if (pagerState.currentPage == 0) pagerState.scrollToPage(1)
            else pagerState.scrollToPage(0)
        }
    }) {
        Text(if (pagerState.currentPage == 0) "Do you have an account?" else "Login now!")
    }
    // endregion
}