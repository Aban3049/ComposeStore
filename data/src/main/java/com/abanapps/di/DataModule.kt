package com.abanapps.di

import android.os.Build
import com.abanapps.NetworkService
import com.abanapps.network.HttpClientFactory
import com.abanapps.repository.ProductRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

val dataModule = module {


    single {
        HttpClientFactory.engine(
            engine = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                CIO.create()
            } else {
                OkHttp.create()
            }
        )
    }

    single {
        NetworkService(get())
    }

    factory<ProductRepository> { NetworkService(get()) }

}