package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme
import dev.justinlee007.scoutstocks.ui.viewmodel.StockDetailViewModel

/**
 * Detailed Stock Information Screen
 * * Present detailed information for a specific stock, such as historical data charts, volume,
 * and market cap, using basic graphical representations and standard UI elements.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockDetailScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    stockDetailViewModel: StockDetailViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stock Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StockDetailScreenPreview() {
    ScoutStocksTheme {
        StockDetailScreen()
    }
}
