import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.entity.User
import org.koin.core.context.startKoin
import sys.component.UserComponent
import sys.module.appModule
import theme.MyApplicationTheme

@Composable
@Preview
fun App(users: List<User>) {

    MyApplicationTheme (darkTheme = true) {
        Column {
            users.forEach {
                Text(it.name)
            }
        }
    }
}

fun main() {
    startKoin {
        modules(appModule())
    }
    val name = UserComponent().getAll()
    application {
        Window(onCloseRequest = ::exitApplication) {
            App(name)
        }
    }
}
