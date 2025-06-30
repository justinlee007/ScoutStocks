package dev.justinlee007.scoutstocks.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.justinlee007.scoutstocks.data.repository.RemoteRepository
import javax.inject.Inject

@HiltViewModel
class StockDetailViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    suspend fun getStockOverview(ticker: String) =
        remoteRepository.getTickerOverview(ticker = ticker)
}