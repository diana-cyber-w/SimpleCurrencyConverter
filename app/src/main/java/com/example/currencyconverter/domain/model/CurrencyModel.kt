package com.example.currencyconverter.domain.model

import com.example.currencyconverter.network.dto.Rates

data class CurrencyModel(
    val status: String,
    val rates: HashMap<String, Rates>?
)

