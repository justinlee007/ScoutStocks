package dev.justinlee007.scoutstocks.domain.model


/**
 * UiState for the overview screen.
 */
sealed class OverviewUiState {

    object Loading : OverviewUiState()

    object Empty : OverviewUiState()

    data class Error(
        val message: String
    ) : OverviewUiState()

    data class Success(
        val items: List<StockItem>,
    ) : OverviewUiState()
}
