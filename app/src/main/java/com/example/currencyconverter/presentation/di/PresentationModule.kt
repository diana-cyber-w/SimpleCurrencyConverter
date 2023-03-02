package com.example.currencyconverter.presentation.di

import com.example.currencyconverter.presentation.viewModel.CurrencyViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        CurrencyViewModel(currencyInteractor = get())
    }
}