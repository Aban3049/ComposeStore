package com.abanapps.di

import com.abanapps.repository.ProductRepository
import com.abanapps.repository.ProductRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> {
        ProductRepositoryImp(
            get()
        )
    }
}