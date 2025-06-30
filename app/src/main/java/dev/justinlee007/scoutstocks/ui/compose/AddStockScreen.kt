package dev.justinlee007.scoutstocks.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun AddStockScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
) {
    var query by remember { mutableStateOf("") }
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
//                    items(filtered) { item ->
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .clickable { onSelect(item) }
//                                .padding(vertical = 8.dp, horizontal = 4.dp)
//                        ) {
//                            Text(
//                                text = item.name,
//                                modifier = Modifier.weight(0.7f)
//                            )
//                            Text(
//                                text = item.ticker,
//                                modifier = Modifier.weight(0.3f)
//                            )
//                        }
//                    }
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