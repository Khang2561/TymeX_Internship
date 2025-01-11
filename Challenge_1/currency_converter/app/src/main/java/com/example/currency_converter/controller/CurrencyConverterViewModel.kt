package com.example.currency_converter.controller
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currency_converter.model.CurrencyConverterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// CurrencyConverterViewModel.kt
// View Model for managing the currency conversion logic and state
class CurrencyConverterViewModel(
    private val repository: CurrencyConverterRepository = CurrencyConverterRepository(apiKey = "9a93e56ab3a1024f4c044def75d9ae6e")
) : ViewModel() {


    // Amount input by the user
    private val _amount = MutableStateFlow("0")
    val amount: StateFlow<String> = _amount

    // Base currency for conversion
    private val _fromCurrency = MutableStateFlow("USD")
    val fromCurrency: StateFlow<String> = _fromCurrency

    // Target currency for conversion
    private val _toCurrency = MutableStateFlow("VND")
    val toCurrency: StateFlow<String> = _toCurrency

    // Converted amount
    private val _convertedAmount = MutableStateFlow<Double?>(null)
    val convertedAmount: StateFlow<Double?> = _convertedAmount

    // List of supported currencies
    private val _supportedCurrencies = MutableStateFlow<Map<String, String>>(emptyMap())
    val supportedCurrencies: StateFlow<Map<String, String>> = _supportedCurrencies

    // Initialize and fetch supported currencies when the ViewModel is created
    init {
        fetchSupportedCurrencies()
    }

    //Update the amount input
    fun onAmountChanged(newAmount: String) {
        _amount.value = newAmount
    }

    // Update the base currency
    fun onFromCurrencyChanged(newCurrency: String) {
        _fromCurrency.value = newCurrency
    }

    //Update the target currency
    fun onToCurrencyChanged(newCurrency: String) {
        _toCurrency.value = newCurrency
    }

    //Convert Event
    fun convert() {
        val amountDouble = _amount.value.toDoubleOrNull()
        // Ensure the entered amount is valid
        if (amountDouble != null) {
            viewModelScope.launch {
                try {
                    // Call the repository to perform the conversion
                    val response = repository.convertCurrency(
                        fromCurrency = _fromCurrency.value,
                        toCurrency = _toCurrency.value,
                        amount = amountDouble
                    )
                    // Assign the result directly
                    _convertedAmount.value = response.result
                } catch (e: Exception) {
                    // Log lỗi nếu có
                    _convertedAmount.value = null
                }
            }
        } else {
            Log.d("CurrencyConverter", "Invalid amount entered")
            _convertedAmount.value = null
        }
    }

    // Fetch supported currencies
    private fun fetchSupportedCurrencies() {
        viewModelScope.launch {
            val currencies = repository.fetchSupportedCurrencies()
            if (currencies != null) {
                _supportedCurrencies.value = currencies
            }
        }
    }
}

