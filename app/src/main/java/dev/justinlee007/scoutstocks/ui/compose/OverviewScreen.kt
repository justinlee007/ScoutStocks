package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.justinlee007.scoutstocks.domain.model.OverviewUiState
import dev.justinlee007.scoutstocks.domain.model.StockItem
import dev.justinlee007.scoutstocks.ui.viewmodel.OverviewViewModel

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    overviewViewModel: OverviewViewModel = hiltViewModel(),
) {
    val uiState = overviewViewModel.uiState.collectAsStateWithLifecycle().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Overview") },
                actions = {
                    IconButton(onClick = { /* TODO: Handle list icon click */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = "List of Stocks"
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
            when (uiState) {
                is OverviewUiState.Loading -> LoadingScreen()
                is OverviewUiState.Error -> ErrorScreen(errorMessage = uiState.message)
                is OverviewUiState.Success -> SuccessScreen(stockItems = uiState.items)
            }
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Loading Stocks")
        }
    }
}

@Composable
fun ErrorScreen(
    errorMessage: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = errorMessage)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SuccessScreen(
    stockItems: List<StockItem>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = stockItems,
            key = { it.ticker }
        ) { stockItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stockItem.name,
                    modifier = Modifier.weight(0.75f)
                )
                Text(
                    text = stockItem.ticker,
                    modifier = Modifier.weight(0.25f)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun PreviewLoadingScreen() {
    LoadingScreen()
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun PreviewErrorScreen() {
    ErrorScreen(errorMessage = "Failed to load stocks.")
}

@Preview(showBackground = true, name = "Success State")
@Composable
fun PreviewSuccessScreen() {
    SuccessScreen(
        stockItems = listOf(
            StockItem(name = "Apple Inc.", ticker = "AAPL"),
            StockItem(name = "Alphabet Inc.", ticker = "GOOGL"),
            StockItem(name = "Microsoft Corp.", ticker = "MSFT")
        )
    )
}
