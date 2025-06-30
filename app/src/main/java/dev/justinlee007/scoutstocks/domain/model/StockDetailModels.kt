package dev.justinlee007.scoutstocks.domain.model

sealed class StockDetailUiState {

    object Loading : StockDetailUiState()

    data class Error(
        val message: String
    ) : StockDetailUiState()

    data class Success(
        val item: StockDetail,
    ) : StockDetailUiState()
}