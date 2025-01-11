package com.example.currency_converter.network

import retrofit2.http.GET
import retrofit2.http.Query

// Data class to represent the response of a currency conversion or exchange rate API call
data class CurrencyResponse(
    val success: Boolean,
    val result: Double
)

// Data class to represent the query parameters for a currency conversion API call
data class Query(
    val from: String,
    val to: String,
    val amount: Double
)

// Data class to hold additional information about the currency conversion or exchange rate
data class Info(
    val timestamp: Long,
    val quote: Double
)

// Data class to represent the response for a list of supported currencies
data class SupportedCurrenciesResponse(
    val success: Boolean,
    val currencies: Map<String, String>?,
    val error: Map<String, String>? = null
)

// Interface defining the Currency API endpoints
interface CurrencyApiService {
    // Endpoint to convert currency
    @GET("convert")
    suspend fun convertCurrency(
        @Query("access_key") apiKey: String,
        @Query("from") fromCurrency: String,
        @Query("to") toCurrency: String,
        @Query("amount") amount: Double
    ): CurrencyResponse

    // Endpoint to retrieve the list of supported currencies
    @GET("list")
    suspend fun getSupportedCurrencies(
        @Query("access_key") apiKey: String
    ): SupportedCurrenciesResponse
}