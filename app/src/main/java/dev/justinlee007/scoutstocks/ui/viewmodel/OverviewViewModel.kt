package dev.justinlee007.scoutstocks.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.justinlee007.scoutstocks.data.repository.StockRepository
import dev.justinlee007.scoutstocks.domain.model.OverviewUiState
import dev.justinlee007.scoutstocks.domain.model.StockItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<OverviewUiState>(OverviewUiState.Loading)

    val uiState: Flow<OverviewUiState> = _uiState.asStateFlow()

    suspend fun refreshUiState() {
        withContext(Dispatchers.IO) {
            stockRepository.getTickerList(limit = 10).map { result ->
                if (result.isSuccess) {
                    val items = result.getOrNull()?.results?.map { ticker ->
                        StockItem(
                            name = ticker.name,
                            ticker = ticker.ticker,
                        )
                    }.orEmpty()
                    OverviewUiState.Success(items = items)
                } else {
                    OverviewUiState.Error(message = result.exceptionOrNull()?.message.orEmpty())
                }
            }
        }.collect { state ->
            withContext(Dispatchers.Main) {
                _uiState.emit(state)
            }
        }
    }
}