package com.example.currency_converter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.currency_converter.ui.CurrencyConverterScreen
import com.example.currency_converter.controller.CurrencyConverterViewModel
import com.example.currency_converter.ui.theme.Currency_converterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = CurrencyConverterViewModel()
            Currency_converterTheme {
                CurrencyConverterScreen(viewModel)
            }
        }
    }
}