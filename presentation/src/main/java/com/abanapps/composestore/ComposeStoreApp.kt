package com.abanapps.composestore

import android.app.Application
import com.abanapps.composestore.di.presentationModule
import com.abanapps.di.dataModule
import com.abanapps.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ComposeStoreApp:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ComposeStoreApp)
            modules(listOf(
                presentationModule,
                dataModule,
                domainModule
            ))
        }

    }
}