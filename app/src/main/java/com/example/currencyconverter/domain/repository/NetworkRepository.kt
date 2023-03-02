package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.network.dto.CurrencyResponse

interface NetworkRepository {

    suspend fun getCurrencyConversion(
        from: String, to: String, amount: Double
    ): CurrencyResponse
}