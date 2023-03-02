package com.example.currencyconverter.domain.interactors

import com.example.currencyconverter.domain.model.CurrencyModel
import com.example.currencyconverter.network.repository.NetworkRepositoryImpl
import com.example.currencyconverter.network.toCurrencyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyInteractorImpl(
    private val networkRepositoryImpl: NetworkRepositoryImpl
) : CurrencyInteractor {

    override suspend fun getCurrencyConversion(
        from: String,
        to: String,
        amount: Double
    ): CurrencyModel {
        return withContext(Dispatchers.IO) {
            networkRepositoryImpl.getCurrencyConversion(from, to, amount).toCurrencyModel()
        }
    }
}