package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import dev.justinlee007.scoutstocks.domain.model.AddStockUiState
import dev.justinlee007.scoutstocks.ui.theme.ScoutStocksTheme
import dev.justinlee007.scoutstocks.ui.viewmodel.AddStockViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Add Stock Screen
 *
 * * Present this screen modally, with a search bar at the top.
 * * Implement an autocomplete search feature by querying the Polygon Stocks API for stock symbols
 * and names as the user types, ensuring the search results are displayed in a basic list format.
 * * Focus on functionality over design for displaying a list of stocks according to the current
 * search query, ensuring efficient API use.
 */
@OptIn(FlowPreview::class)
@Composable
fun AddStockScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    addStockViewModel: AddStockViewModel = hiltViewModel(),
) {
    var query by remember { mutableStateOf("") }
    val uiState = addStockViewModel.uiState.collectAsStateWithLifecycle(
        initialValue = AddStockUiState.Empty
    ).value
    // Debounce query changes
    LaunchedEffect(Unit) {
        snapshotFlow { query }
            .debounce(timeoutMillis = 500)
            .distinctUntilChanged()
            .collectLatest { debouncedQuery ->
                addStockViewModel.searchStock(query)
            }
    }

    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = onBack) { Text("Close") }
        },
        title = { Text("Add Stock") },
        text = {
            Column {
                TextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Search") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
                ) {
                    when (uiState) {
                        is AddStockUiState.Empty -> {
                            item {
                                Text(
                                    text = "No results found",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }
                        }

                        is AddStockUiState.Success -> {
                            items(uiState.items) { item ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            addStockViewModel.viewModelScope.launch {
                                                Timber.d("Adding stock: ${item.ticker}")
                                                addStockViewModel.addStockItem(item)
                                            }
                                            onBack()
                                        }
                                        .padding(vertical = 8.dp, horizontal = 4.dp)
                                ) {
                                    Text(
                                        text = item.name,
                                        modifier = Modifier.weight(0.7f)
                                    )
                                    Text(
                                        text = item.ticker,
                                        modifier = Modifier.weight(0.3f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AddStockScreenPreview() {
    ScoutStocksTheme {
        AddStockScreen()
    }
}