package com.example.currency_converter.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    // Basic URL for the Currency the API
    private const val BASE_URL = "https://api.currencylayer.com/"

    // Logging Interceptor to log HTTP requests and responses for debugging
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // log the body of the request and response
    }

    // OkHttpClient configured with the logging interceptor
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Adds the logging interceptor
        .build() // Builds the OkHttpClient instance

    // Retrofit instance configured with the base URL, OkHttpClient, and Gson converter
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // Set the base URL for call API
        .client(httpClient) // Attaches the configured OkHttpClient
        .addConverterFactory(GsonConverterFactory.create()) // Adds Gson converter to handle JSON
        .build()
}