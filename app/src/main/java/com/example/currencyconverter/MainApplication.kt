package com.example.currencyconverter

import android.app.Application
import com.example.currencyconverter.domain.di.domainModule
import com.example.currencyconverter.network.di.networkModule
import com.example.currencyconverter.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(
                networkModule,
                domainModule,
                presentationModule
            )
        }
    }
}