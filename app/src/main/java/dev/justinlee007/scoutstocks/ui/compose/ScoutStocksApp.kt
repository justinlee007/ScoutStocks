package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.runtime.Composable
import dev.justinlee007.scoutstocks.ScoutStocksNavHost
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme

@Composable
fun ScoutStocksApp() {
    ScoutStocksTheme {
        ScoutStocksNavHost()
    }
}