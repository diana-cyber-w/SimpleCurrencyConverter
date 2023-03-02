package com.example.currencyconverter.network

import com.example.currencyconverter.domain.model.CurrencyModel
import com.example.currencyconverter.network.dto.CurrencyResponse

fun CurrencyResponse.toCurrencyModel() = CurrencyModel(status, rates)