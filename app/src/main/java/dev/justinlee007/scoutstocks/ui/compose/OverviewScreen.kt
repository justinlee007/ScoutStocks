package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme

/**
 * Stocks Overview Screen
 * * Display a list of stocks with a summary (current price, daily change) of each stock in a
 * simple, easy-to-read format.
 * * Include a button in the top right corner of the screen to navigate to a “List of Stocks”
 * screen.
 * * Open a “Detailed Stock Information” screen when tapping on a specific stock.
 * * Focus on presenting the essential data clearly.
 * * Implement a pull-to-refresh feature to update stock data
 * * Fetch new data every time the app starts, displaying this information in a no-frills,
 * accessible manner.
 */
@Composable
fun OverviewScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun OverviewScreenPreview() {
    ScoutStocksTheme {
        OverviewScreen("Android")
    }
}