package com.example.currencyconverter.network

import com.example.currencyconverter.network.dto.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("convert")
    suspend fun getCurrencyConversion(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double,
        @Query("format") format: String,
        @Query("api_key") apiKey: String = RetrofitClient.BASE_API_KEY
    ): CurrencyResponse
}