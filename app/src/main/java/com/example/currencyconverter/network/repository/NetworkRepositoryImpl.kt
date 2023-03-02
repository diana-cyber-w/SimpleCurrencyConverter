package com.example.currencyconverter.network.repository

import com.example.currencyconverter.domain.repository.NetworkRepository
import com.example.currencyconverter.network.CurrencyApi
import com.example.currencyconverter.network.dto.CurrencyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepositoryImpl(private val currencyApi: CurrencyApi) : NetworkRepository {

    override suspend fun getCurrencyConversion(
        from: String,
        to: String,
        amount: Double
    ): CurrencyResponse {
        return withContext(Dispatchers.IO) {
            currencyApi.getCurrencyConversion(from, to, amount, FORMAT)
        }
    }

    companion object {
        private const val FORMAT = "json"
    }
}