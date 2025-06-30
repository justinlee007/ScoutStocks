package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme

/**
 * Add Stock Screen
 *
 * * Present this screen modally, with a search bar at the top.
 * * Implement an autocomplete search feature by querying the Polygon Stocks API for stock symbols
 * and names as the user types, ensuring the search results are displayed in a basic list format.
 * * Focus on functionality over design for displaying a list of stocks according to the current
 * search query, ensuring efficient API use.
 */
@Composable
fun AddStockScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AddStockScreenPreview() {
    ScoutStocksTheme {
        AddStockScreen("Android")
    }
}