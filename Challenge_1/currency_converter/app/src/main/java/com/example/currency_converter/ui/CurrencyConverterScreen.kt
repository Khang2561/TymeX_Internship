package com.example.currency_converter.ui
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.currency_converter.controller.CurrencyConverterViewModel
import com.example.currency_converter.ui.components.CurrencyDropdown
import java.text.DecimalFormat

// CurrencyConverterScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyConverterScreen(viewModel: CurrencyConverterViewModel) {
    //----------------------------CONST-------------------------------------------------------------
    val amount = viewModel.amount.collectAsState()
    val fromCurrency = viewModel.fromCurrency.collectAsState()
    val toCurrency = viewModel.toCurrency.collectAsState()
    val convertedAmount = viewModel.convertedAmount.collectAsState()
    val currencies = viewModel.supportedCurrencies.collectAsState()

    //----------------------------FUNCTION----------------------------------------------------------
    // Number format function
    fun formatAmount(amount: Double): String {
        val formatter = DecimalFormat("#,###.##")
        return formatter.format(amount)
    }

    //----------------------------UI----------------------------------------------------------------
    Scaffold(
        //--------------------TOP----------------------
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Currency Converter",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        //------------------CONTENT----------------------
        content = { paddingValues ->
            if (currencies.value.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
                return@Scaffold
            }

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Convert your currency easily",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Medium
                    )
                )

                // Input amount
                TextField(
                    value = amount.value,
                    onValueChange = {
                        if (it.isEmpty() || it.matches(Regex("\\d*\\.?\\d*"))) {
                            viewModel.onAmountChanged(it)
                        }
                    },
                    label = { Text("Enter Amount") },
                    placeholder = { Text("e.g., 100.0") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
                    )
                )

                // Choose from currency
                CurrencyDropdown(
                    label = "From Currency",
                    selectedCurrency = fromCurrency.value,
                    currencies = currencies.value.keys.toList(),
                    onCurrencySelected = { viewModel.onFromCurrencyChanged(it) }
                )

                // Choose to currency
                CurrencyDropdown(
                    label = "To Currency",
                    selectedCurrency = toCurrency.value,
                    currencies = currencies.value.keys.toList(),
                    onCurrencySelected = { viewModel.onToCurrencyChanged(it) }
                )

                // Convert button
                Button(
                    onClick = { viewModel.convert() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Convert", color = MaterialTheme.colorScheme.onPrimary)
                }

                // Display converted amount
                convertedAmount.value?.let { amount ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Text(
                            text = "Converted Amount: ${formatAmount(amount)} ${toCurrency.value}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    )
}