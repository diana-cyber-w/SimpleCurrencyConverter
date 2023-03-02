package com.example.currencyconverter.domain.di

import com.example.currencyconverter.domain.interactors.CurrencyInteractor
import com.example.currencyconverter.domain.interactors.CurrencyInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    single<CurrencyInteractor> {
        CurrencyInteractorImpl(networkRepositoryImpl = get())
    }
}