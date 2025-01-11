package com.example.currency_converter.model

import android.util.Log
import com.example.currency_converter.network.CurrencyApiService
import com.example.currency_converter.network.NetworkModule
import com.example.currency_converter.network.CurrencyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyConverterRepository(private val apiKey: String) {
    // Initialize the CurrencyApiService Retrofit
    private val apiService = NetworkModule.retrofit.create(CurrencyApiService::class.java)

    // Function to fetch the list of supported currencies
    suspend fun fetchSupportedCurrencies(): Map<String, String>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getSupportedCurrencies(apiKey)
                if (response.success) {
                    response.currencies
                } else {
                    println("Error: ${response.error}")
                    null
                }
            } catch (e: Exception) {
                println("Exception: ${e.message}")
                null
            }
        }
    }

    // Function to convert currency
    suspend fun convertCurrency(fromCurrency: String, toCurrency: String, amount: Double): CurrencyResponse {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.convertCurrency(
                    apiKey = apiKey,
                    fromCurrency = fromCurrency,
                    toCurrency = toCurrency,
                    amount = amount
                )
                response // Success
            } catch (e: Exception) {
                Log.e("CurrencyConverter", "Error converting currency: ${e.message}")
                CurrencyResponse(success = false, result = 0.0) // Error
            }
        }
    }
}
