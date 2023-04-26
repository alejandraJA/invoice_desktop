package sys.module

import data.repository.UserRepository
import data.repository.UserRepositoryImp
import org.koin.dsl.module
import service.UserService

fun appModule() = module {
    single<UserRepository> { UserRepositoryImp() }
    factory { UserService(get()) }
}