package dev.justinlee007.scoutstocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.justinlee007.scoutstocks.domain.model.StockItem
import dev.justinlee007.scoutstocks.ui.compose.ScoutStocksApp
import dev.justinlee007.scoutstocks.ui.viewmodel.AddStockViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ScoutStocksActivity : ComponentActivity() {

    private val addStockViewModel by viewModels<AddStockViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeSomeStocks()
        enableEdgeToEdge()
        setContent {
            ScoutStocksApp()
        }
    }

    private fun initializeSomeStocks() {
        try {
            val preferences = getSharedPreferences("scout_stocks", MODE_PRIVATE)
            val isFirstLaunch = preferences.getBoolean("first_launch", true)
            if (isFirstLaunch) {
                val apple = StockItem(name = "Apple Inc.", ticker = "AAPL")
                val google = StockItem(name = "Alphabet Inc.", ticker = "GOOGL")
                val microsoft = StockItem(name = "Microsoft Corp.", ticker = "MSFT")
                lifecycleScope.launch {
                    try {
                        addStockViewModel.addStockItem(apple)
                        addStockViewModel.addStockItem(google)
                        addStockViewModel.addStockItem(microsoft)
                    } catch (e: Exception) {
                        Timber.e(e, "Error adding initial stocks")
                    }
                }
                preferences.edit { putBoolean("first_launch", false) }
            }
        } catch (e: Exception) {
            Timber.e(e, "Error initializing stocks")
        }
    }
}