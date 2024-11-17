package com.abanapps.di

import com.abanapps.NetworkService
import com.abanapps.network.HttpClientFactory
import com.abanapps.repository.ProductRepository
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val dataModule = module {

    single {
        HttpClientFactory.engine(CIO.create())
    }

    single {
        NetworkService(get())
    }

    // Provide Repository Implementation (binds to the domain interface)
    factory<ProductRepository> { NetworkService(get()) }

}