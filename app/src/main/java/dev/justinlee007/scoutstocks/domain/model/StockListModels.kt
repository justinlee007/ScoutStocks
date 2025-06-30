package dev.justinlee007.scoutstocks.domain.model

/**
 * UiState for the stock list screen.
 */
sealed class StockListUiState {

    object Empty : StockListUiState()

    data class Error(
        val message: String
    ) : StockListUiState()

    data class Success(
        val items: List<StockItem>,
    ) : StockListUiState()
}
