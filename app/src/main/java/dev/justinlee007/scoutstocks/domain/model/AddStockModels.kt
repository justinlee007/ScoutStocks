package dev.justinlee007.scoutstocks.domain.model

/**
 * UiState for the stock list screen.
 */
sealed class AddStockUiState {

    object Empty : AddStockUiState()

    data class Success(
        val items: List<StockItem>,
    ) : AddStockUiState()
}
