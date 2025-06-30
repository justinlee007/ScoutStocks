package dev.justinlee007.scoutstocks.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.justinlee007.scoutstocks.data.repository.RemoteRepository
import dev.justinlee007.scoutstocks.domain.model.StockDetail
import dev.justinlee007.scoutstocks.domain.model.StockDetailUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StockDetailViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<StockDetailUiState>(StockDetailUiState.Loading)

    val uiState: Flow<StockDetailUiState> = _uiState.asStateFlow()

    suspend fun refreshUiState(ticker: String) {
        val result =
            withContext(Dispatchers.IO) { remoteRepository.getTickerOverview(ticker = ticker) }
        withContext(Dispatchers.Main) {
            if (result.isSuccess) {
                val tickerOverview = result.getOrNull()
                val tickerResult = tickerOverview?.tickerResult
                if (tickerOverview != null && tickerResult != null) {
                    _uiState.emit(
                        StockDetailUiState.Success(
                            item = StockDetail(
                                name = tickerResult.name,
                                ticker = ticker,
                                volume = tickerResult.marketCap,
                                marketCap = tickerResult.marketCap,
                            )
                        )
                    )
                } else {
                    _uiState.emit(StockDetailUiState.Error(message = "No data found for $ticker"))
                }
            } else {
                _uiState.emit(
                    StockDetailUiState.Error(
                        message = result.exceptionOrNull()?.message ?: "Unknown error"
                    )
                )
            }
        }
    }
}