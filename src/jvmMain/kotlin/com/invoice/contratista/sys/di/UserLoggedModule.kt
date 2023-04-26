package com.invoice.contratista.sys.di

import org.koin.dsl.module
import com.invoice.contratista.sys.service.UserService
import com.invoice.contratista.sys.domain.repository.local.UserLoggedRepository
import com.invoice.contratista.data.repository.local.LocalLoggedRepositoryImp

fun appModule() = module {
    single<UserLoggedRepository> { LocalLoggedRepositoryImp() }
    factory { UserService(get()) }
}