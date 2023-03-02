package com.example.currencyconverter.domain.interactors

import com.example.currencyconverter.domain.model.CurrencyModel

interface CurrencyInteractor {
    suspend fun getCurrencyConversion(
        from: String, to: String, amount: Double
    ): CurrencyModel
}