package dev.justinlee007.scoutstocks.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.justinlee007.scoutstocks.data.local.LocalTicker
import dev.justinlee007.scoutstocks.data.repository.LocalRepository
import dev.justinlee007.scoutstocks.data.repository.RemoteRepository
import dev.justinlee007.scoutstocks.domain.model.AddStockUiState
import dev.justinlee007.scoutstocks.domain.model.StockItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddStockViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddStockUiState>(AddStockUiState.Empty)

    val uiState: Flow<AddStockUiState> = _uiState.asStateFlow()

    suspend fun searchStock(query: String) {
        val result = withContext(Dispatchers.IO) {
            remoteRepository.getTickerList(search = query)
        }
        withContext(Dispatchers.Main) {
            if (result.isSuccess) {
                val tickerList = result.getOrNull()?.results.orEmpty()
                if (tickerList.isEmpty()) {
                    _uiState.emit(AddStockUiState.Empty)
                } else {
                    val stockItems = tickerList.map { tickerOverview ->
                        StockItem(
                            name = tickerOverview.name,
                            ticker = tickerOverview.ticker,
                        )
                    }
                    _uiState.emit(AddStockUiState.Success(items = stockItems))
                }
            } else {
                _uiState.emit(AddStockUiState.Empty)
            }
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
}