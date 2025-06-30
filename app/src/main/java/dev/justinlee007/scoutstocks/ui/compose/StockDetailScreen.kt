package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import dev.justinlee007.scoutstocks.domain.model.StockDetail
import dev.justinlee007.scoutstocks.domain.model.StockDetailUiState
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme
import dev.justinlee007.scoutstocks.ui.viewmodel.StockDetailViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

/**
 * Detailed Stock Information Screen
 * * Present detailed information for a specific stock, such as historical data charts, volume,
 * and market cap, using basic graphical representations and standard UI elements.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockDetailScreen(
    ticker: String,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    stockDetailViewModel: StockDetailViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = ticker) {
        stockDetailViewModel.refreshUiState(ticker = ticker)
    }
    val uiState = stockDetailViewModel.uiState.collectAsStateWithLifecycle(
        initialValue = StockDetailUiState.Loading
    ).value
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
            when (uiState) {
                is StockDetailUiState.Loading -> DetailLoadingScreen(ticker = ticker)
                is StockDetailUiState.Error -> DetailErrorScreen(
                    errorMessage = uiState.message,
                    onRefresh = {
                        stockDetailViewModel.viewModelScope.launch {
                            stockDetailViewModel.refreshUiState(ticker)
                        }
                    }
                )

                is StockDetailUiState.Success -> DetailSuccessScreen(stockDetail = uiState.item)
            }
        }
    }
}

@Composable
fun DetailLoadingScreen(
    ticker: String,
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
            Text(text = "Loading $ticker details...")
        }
    }
}

@Composable
fun DetailErrorScreen(
    errorMessage: String,
    onRefresh: () -> Unit,
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
            Button(onClick = onRefresh) {
                Text("Retry")
            }
        }
    }
}

@Composable
fun DetailSuccessScreen(
    stockDetail: StockDetail,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val formatter = NumberFormat.getNumberInstance(Locale.US)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Name: ${stockDetail.name}")
            Text(text = "Ticker: ${stockDetail.ticker}")
            Text(text = "Volume: ${formatter.format(stockDetail.volume ?: 0)}")
            Text(text = "Market Cap: ${formatter.format(stockDetail.marketCap ?: 0)}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StockDetailScreenPreview() {
    ScoutStocksTheme {
        StockDetailScreen(ticker = "AAPL")
    }
}
