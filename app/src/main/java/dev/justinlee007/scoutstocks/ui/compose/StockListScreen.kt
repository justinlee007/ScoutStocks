package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import dev.justinlee007.scoutstocks.domain.model.StockItem
import dev.justinlee007.scoutstocks.domain.model.StockListUiState
import dev.justinlee007.scoutstocks.ui.viewmodel.StockListViewModel
import kotlinx.coroutines.launch

/**
 * List of Stocks Screen
 * * Show a list of stocks with names and symbols in a straightforward list view.
 * * Add a button in the top right corner of the screen to navigate to an “Add Stock” screen
 * * Implement a swipe-to-delete feature that removes a stock from the list and clears any related
 * data from storage, keeping the UI simple.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onClickAddStock: () -> Unit = {},
    stockListViewModel: StockListViewModel = hiltViewModel(),
) {
    val uiState = stockListViewModel.getStockList().collectAsStateWithLifecycle(
        initialValue = StockListUiState.Empty
    ).value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stock List") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Handle list icon click */ }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Stock"
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
                is StockListUiState.Empty -> EmptyListScreen()
                is StockListUiState.Error -> ErrorListScreen(errorMessage = uiState.message)
                is StockListUiState.Success -> SuccessListScreen(
                    stockItems = uiState.items,
                    onDeleteStock = { stockItem ->
                        stockListViewModel.viewModelScope.launch {
                            stockListViewModel.deleteStockItem(stockItem)
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun EmptyListScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No Stocks Added")
    }
}

@Composable
fun ErrorListScreen(
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
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessListScreen(
    stockItems: List<StockItem>,
    modifier: Modifier = Modifier,
    onDeleteStock: (StockItem) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Name",
                    modifier = Modifier.weight(0.75f),
                )
                Text(
                    text = "Ticker",
                    modifier = Modifier.weight(0.25f),
                )
            }
        }
        items(
            items = stockItems,
            key = { it.ticker }
        ) { stockItem ->
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = { newValue ->
                    if (newValue == SwipeToDismissBoxValue.StartToEnd) {
                        onDeleteStock
                        true
                    } else {
                        false
                    }
                },
                positionalThreshold = { it * .25f }
            )
            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = {
                    val color = when (dismissState.dismissDirection) {
                        SwipeToDismissBoxValue.StartToEnd -> Color.Red
                        else -> Color.Transparent
                    }
                    if (dismissState.dismissDirection.name == SwipeToDismissBoxValue.StartToEnd.name) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "delete")
                        }
                    }
                },
                enableDismissFromEndToStart = false,
            ) {
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
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun PreviewErrorListScreen() {
    ErrorListScreen(
        errorMessage = "Failed to load stocks.",
    )
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun PreviewEmptyListScreen() {
    EmptyListScreen()
}

@Preview(showBackground = true, name = "Success State")
@Composable
fun PreviewSuccessListScreen() {
    SuccessListScreen(
        stockItems = listOf(
            StockItem(name = "Apple Inc.", ticker = "AAPL"),
            StockItem(name = "Alphabet Inc.", ticker = "GOOGL"),
            StockItem(name = "Microsoft Corp.", ticker = "MSFT")
        ),
    )
}
