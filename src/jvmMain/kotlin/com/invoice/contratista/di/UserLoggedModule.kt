package com.invoice.contratista.di

import org.koin.dsl.module
import com.invoice.contratista.service.UserService
import com.invoice.contratista.domain.UserLoggedRepository
import com.invoice.contratista.data.repository.local.LocalLoggedRepositoryImp

fun appModule() = module {
    single<UserLoggedRepository> { LocalLoggedRepositoryImp() }
    factory { UserService(get()) }
}