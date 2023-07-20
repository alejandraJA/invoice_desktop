package com.invoice.contratista.di

import com.invoice.contratista.data.repository.web.CustomerRepositoryImp
import com.invoice.contratista.data.repository.web.EventRepositoryImp
import com.invoice.contratista.data.repository.web.ProductRepositoryImp
import com.invoice.contratista.data.repository.web.SingRepositoryImp
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.domain.CustomerRepository
import com.invoice.contratista.domain.EventRepository
import com.invoice.contratista.domain.ProductRepository
import com.invoice.contratista.domain.SingRepository
import com.invoice.contratista.service.EventService
import com.invoice.contratista.service.ProductService
import com.invoice.contratista.service.SingService
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

    factory<ProductService> { ProductService(get(), get()) }
    factory<EventService> { EventService(get(), get()) }
    factory<SingService> { SingService(get(), get()) }
}