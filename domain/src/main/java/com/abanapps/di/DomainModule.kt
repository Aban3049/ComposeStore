package com.abanapps.di

import com.abanapps.repository.ProductRepository
import com.abanapps.useCase.GetProductUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductUseCase(get()) }
}