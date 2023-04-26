package sys.component

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import service.UserService

class UserComponent : KoinComponent {
    private val userService: UserService by inject()

    fun getAll() = userService.getAll()
}