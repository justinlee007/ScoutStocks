package dev.justinlee007.scoutstocks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.justinlee007.scoutstocks.data.local.LocalTicker
import dev.justinlee007.scoutstocks.data.repository.LocalRepository
import dev.justinlee007.scoutstocks.domain.model.StockItem
import dev.justinlee007.scoutstocks.domain.model.StockListUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    init {
        val apple = LocalTicker(name = "Apple Inc.", ticker = "AAPL")
        val google = LocalTicker(name = "Alphabet Inc.", ticker = "GOOGL")
        val microsoft = LocalTicker(name = "Microsoft Corp.", ticker = "MSFT")
        viewModelScope.launch {
            localRepository.upsertTicker(ticker = apple)
            localRepository.upsertTicker(ticker = google)
            localRepository.upsertTicker(ticker = microsoft)
        }
    }

    fun getStockList(): Flow<StockListUiState> = localRepository.getAllTickers().map { items ->
        if (items.isEmpty()) {
            StockListUiState.Empty
        } else {
            StockListUiState.Success(items = items.map {
                StockItem(
                    name = it.name,
                    ticker = it.ticker,
                )
            })
        }
    }

    suspend fun addStockItem(stockItem: StockItem) {
        localRepository.upsertTicker(
            ticker = LocalTicker(
                name = stockItem.name,
                ticker = stockItem.ticker,
            )
        )
    }

    suspend fun deleteStockItem(stockItem: StockItem) {
        localRepository.deleteTickerBySymbol(stockItem.ticker)
    }
}