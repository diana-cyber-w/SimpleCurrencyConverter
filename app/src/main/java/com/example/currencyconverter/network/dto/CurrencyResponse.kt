package com.example.currencyconverter.network.dto

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_date")
    val updated_date: String?,
    @SerializedName("base_currency_code")
    val base_currency_code: String?,
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("base_currency_name")
    val base_currency_name: String?,
    @SerializedName("rates")
    val rates: HashMap<String, Rates>
)

data class Rates(
    @SerializedName("currency_name")
    val currency_name: String?,
    @SerializedName("rate")
    val rate: Double?,
    @SerializedName("rate_for_amount")
    val rate_for_amount: Double?
)
