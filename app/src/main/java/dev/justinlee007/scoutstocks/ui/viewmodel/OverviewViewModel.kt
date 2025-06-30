package dev.justinlee007.scoutstocks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.justinlee007.scoutstocks.data.repository.LocalRepository
import dev.justinlee007.scoutstocks.data.repository.RemoteRepository
import dev.justinlee007.scoutstocks.domain.model.OverviewUiState
import dev.justinlee007.scoutstocks.domain.model.StockItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            refreshUiState()
        }
    }

    private val _uiState = MutableStateFlow<OverviewUiState>(OverviewUiState.Loading)

    val uiState: Flow<OverviewUiState> = _uiState.asStateFlow()

    suspend fun refreshUiState() {
        localRepository.getAllTickers().collect { tickers ->
            if (tickers.isEmpty()) {
                _uiState.emit(OverviewUiState.Empty)
            } else {
                val results = withContext(Dispatchers.IO) {
                    tickers.map { localTicker ->
                        async {
                            val result = remoteRepository.getPreviousDayBar(
                                stocksTicker = localTicker.ticker,
                            )
                            result.getOrNull()?.let { previousDayBar ->
                                previousDayBar.results?.firstOrNull()?.let { aggregateBar ->
                                    StockItem(
                                        name = localTicker.name,
                                        ticker = localTicker.ticker,
                                        price = aggregateBar.close,
                                        change = aggregateBar.close - aggregateBar.open,
                                        changePercent = if (aggregateBar.open != 0.0) {
                                            (aggregateBar.close - aggregateBar.open) / aggregateBar.open * 100
                                        } else {
                                            null
                                        }
                                    )
                                }
                            }
                        }
                    }.awaitAll()
                }
                withContext(Dispatchers.Main) {
                    _uiState.emit(OverviewUiState.Success(items = results.filterNotNull()))
                }
            }
        }
    }
}