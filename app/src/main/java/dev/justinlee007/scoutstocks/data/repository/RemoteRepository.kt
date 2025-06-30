package dev.justinlee007.scoutstocks.data.repository

import dev.justinlee007.scoutstocks.data.remote.PolygonApiService
import dev.justinlee007.scoutstocks.data.remote.PreviousDayBar
import dev.justinlee007.scoutstocks.data.remote.Ticker
import dev.justinlee007.scoutstocks.data.remote.TickerList
import dev.justinlee007.scoutstocks.data.remote.TickerOverview
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository class responsible for fetching stock-related data.
 * It abstracts the data source (PolygonApiService) from the rest of the application.
 */
@Singleton
class RemoteRepository @Inject constructor(
    private val apiService: PolygonApiService
) {

    /**
     * Fetches a list of tickers based on provided criteria.
     *
     * @param market Filter by market type (defaults to "stocks").
     * @param active Filter by actively traded assets (defaults to true).
     * @param search Search for terms within the ticker and/or company name.
     * @param limit Limit the number of results returned.
     * @param sort Sort field used for ordering.
     * @param order Order results based on the sort field.
     * @return A [TickerList] object.
     */
    suspend fun getTickerList(
        market: String = "stocks",
        active: Boolean = true,
        search: String? = null,
        limit: Int? = null,
        sort: String? = null,
        order: String? = null
    ): Result<TickerList> {
        delay(2000)
        val response = apiService.getTickerList(
            market = market,
            active = active,
            search = search,
            limit = limit,
            sort = sort,
            order = order
        )
        return if (response.isSuccessful) {
            val tickerList = response.body()
            if (tickerList != null) {
                Result.success(tickerList)
            } else {
                Result.failure(Exception("Empty response body"))
            }
        } else {
            Result.failure(Exception("API request failed with code ${response.code()}"))
        }
    }

    /**
     * Fetches the previous trading day's bar data (OHLCV) for a specific ticker.
     *
     * @param stocksTicker The ticker symbol (e.g., "AAPL").
     * @param adjusted Whether or not the results are adjusted for splits.
     * @return A [PreviousDayBar] object. The actual bar data will be in `response.results?.firstOrNull()`.
     */
    suspend fun getPreviousDayBar(
        stocksTicker: String,
        adjusted: Boolean = true
    ): Result<PreviousDayBar> {
        val response = apiService.getPreviousDayBar(
            stocksTicker = stocksTicker,
            adjusted = adjusted
        )
        return if (response.isSuccessful) {
            val previousDayBar = response.body()
            if (previousDayBar != null) {
                Result.success(previousDayBar)
            } else {
                Result.failure(Exception("Empty response body"))
            }
        } else {
            Result.failure(Exception("API request failed with code ${response.code()}"))
        }
    }

    /**
     * Fetches detailed information for a single ticker.
     *
     * @param ticker The ticker symbol of the asset (e.g., "AAPL").
     * @return A [TickerOverview] object, which typically contains a single [Ticker] in its results list.
     */
    suspend fun getTickerOverview(ticker: String): Result<TickerOverview> {
        val response = apiService.getTickerOverview(ticker = ticker)
        return if (response.isSuccessful) {
            val tickerOverview = response.body()
            if (tickerOverview != null) {
                Result.success(tickerOverview)
            } else {
                Result.failure(Exception("Empty response body"))
            }
        } else {
            Result.failure(Exception("API request failed with code ${response.code()}"))
        }
    }
}