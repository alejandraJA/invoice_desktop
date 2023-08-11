package com.invoice.contratista

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import kotlin.system.exitProcess

@Composable
fun ChangedStateComponent(windowState: WindowState, fullscreen: MutableState<Boolean>) = Row {
    Spacer(modifier = Modifier.weight(1f))

    Icon(
        painter = painterResource("drawables/remove.svg"),
        contentDescription = "minimised",
        modifier = Modifier.size(48.dp, 24.dp).clickable {
            windowState.isMinimized = !windowState.isMinimized
        }
    )
    Icon(
        painter = painterResource(
            "drawables/${
                if (!fullscreen.value) "fullscreen"
                else "close_fullscreen"
            }.svg"
        ),
        contentDescription = "resize",
        modifier = Modifier.size(48.dp, 24.dp).clickable {
            windowState.placement =
                if (fullscreen.value) WindowPlacement.Floating
                else {
                    windowState.position = WindowPosition(x = 0.dp, y = 0.dp)
                    WindowPlacement.Maximized
                }
            fullscreen.value = !fullscreen.value
        }
    )
    Icon(
        painter = painterResource("drawables/close.svg"),
        contentDescription = "exit",
        modifier = Modifier.size(48.dp, 24.dp)
            .clickable {
                exitProcess(0)
            }
    )
}
