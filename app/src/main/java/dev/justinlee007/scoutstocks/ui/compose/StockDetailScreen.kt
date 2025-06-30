package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme

/**
 * Detailed Stock Information Screen
 * * Present detailed information for a specific stock, such as historical data charts, volume,
 * and market cap, using basic graphical representations and standard UI elements.
 */
@Composable
fun StockDetailScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
) {
    Text(
        text = "Stock Detail Screen",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun StockDetailScreenPreview() {
    ScoutStocksTheme {
        StockDetailScreen()
    }
}
