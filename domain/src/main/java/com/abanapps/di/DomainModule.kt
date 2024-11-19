package com.abanapps.di

import com.abanapps.useCase.GetFeaturedProductUseCase
import com.abanapps.useCase.GetPopularProductUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetFeaturedProductUseCase(get()) }
    factory { GetPopularProductUseCase(get()) }
}