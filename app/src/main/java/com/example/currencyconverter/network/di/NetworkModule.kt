package com.example.currencyconverter.network.di

import com.example.currencyconverter.network.RetrofitClient
import com.example.currencyconverter.network.repository.NetworkRepositoryImpl
import org.koin.dsl.module

val networkModule = module {
    single {
        RetrofitClient.getBookApi()
    }

    single {
        NetworkRepositoryImpl(currencyApi = get())
    }
}