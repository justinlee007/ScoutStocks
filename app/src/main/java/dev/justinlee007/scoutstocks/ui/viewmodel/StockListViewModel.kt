package dev.justinlee007.scoutstocks.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.justinlee007.scoutstocks.data.remote.TickerList
import dev.justinlee007.scoutstocks.data.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {

    suspend fun fetchTickerList(): Flow<Result<TickerList>> {
        return stockRepository.getTickerList(

        )
    }
}