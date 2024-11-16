package com.abanapps.di

import com.abanapps.network.networkModule
import org.koin.dsl.module

val dataModule = module {

    includes(networkModule, repositoryModule)

}