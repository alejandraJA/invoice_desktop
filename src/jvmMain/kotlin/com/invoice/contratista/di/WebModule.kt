package com.invoice.contratista.di

import com.invoice.contratista.data.repository.web.*
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.domain.*
import com.invoice.contratista.service.*
import com.invoice.contratista.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun webModule() = module {

    factory<OkHttpClient> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .build()
    }
    single<Service> {
        val retrofit: Retrofit = get()
        retrofit.create(Service::class.java)
    }

    factory<CustomerRepository> { CustomerRepositoryImp(get()) }
    factory<EventRepository> { EventRepositoryImp(get()) }
    factory<SingRepository> { SingRepositoryImp(get()) }
    factory<ProductRepository> { ProductRepositoryImp(get()) }
    factory<ReservedRepository> { ReservedRepositoryImp(get()) }
    factory<BudgetRepository> { BudgetRepositoryImp(get()) }

    factory<ProductService> { ProductService(get(), get()) }
    factory<EventService> { EventService(get(), get()) }
    factory<SingService> { SingService(get(), get()) }
    factory<ReservedService> { ReservedService(get(), get()) }
    factory<BudgetService> { BudgetService(get(), get()) }
}