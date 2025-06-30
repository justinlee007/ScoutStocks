package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme

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