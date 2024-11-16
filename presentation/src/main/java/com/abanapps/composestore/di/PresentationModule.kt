package com.abanapps.composestore.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}