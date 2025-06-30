package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme

/**
 * List of Stocks Screen
 * * Show a list of stocks with names and symbols in a straightforward list view.
 * * Add a button in the top right corner of the screen to navigate to an “Add Stock” screen
 * * Implement a swipe-to-delete feature that removes a stock from the list and clears any related
 * data from storage, keeping the UI simple.
 */
@Composable
fun StockListScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun StockListScreenPreview() {
    ScoutStocksTheme {
        StockListScreen("Android")
    }
}
