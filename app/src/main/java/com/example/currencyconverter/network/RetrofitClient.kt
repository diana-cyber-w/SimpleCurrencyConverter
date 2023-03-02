package com.example.currencyconverter.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.getgeoapi.com/v2/currency/"
    const val BASE_API_KEY = "756a1702dc7224cd766c47a62f1daa2c78701b17"

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getClient(url: String = BASE_URL) = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getBookApi(): CurrencyApi = getClient().create(CurrencyApi::class.java)
}