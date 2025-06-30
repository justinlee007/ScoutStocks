package dev.justinlee007.scoutstocks.data.repository

import dev.justinlee007.scoutstocks.data.model.Ticker
import dev.justinlee007.scoutstocks.data.model.TickerList
import dev.justinlee007.scoutstocks.data.model.TickerOverview
import dev.justinlee007.scoutstocks.data.remote.PolygonApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository class responsible for fetching stock-related data.
 * It abstracts the data source (PolygonApiService) from the rest of the application.
 */
@Singleton
class StockRepository @Inject constructor(
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
    ): Flow<Result<TickerList>> {
        delay(2000)
        val response = apiService.getTickerList(
            market = market,
            active = active,
            search = search,
            limit = limit,
            sort = sort,
            order = order
        )
        val result = if (response.isSuccessful) {
            val tickerList = response.body()
            if (tickerList != null) {
                Result.success(tickerList)
            } else {
                Result.failure(Exception("Empty response body"))
            }
        } else {
            Result.failure(Exception("API request failed with code ${response.code()}"))
        }
        return flowOf(result)
    }

    /**
     * Fetches detailed information for a single ticker.
     *
     * @param ticker The ticker symbol of the asset (e.g., "AAPL").
     * @return A [TickerOverview] object, which typically contains a single [Ticker] in its results list.
     */
    suspend fun getTickerOverview(ticker: String): Flow<Result<TickerOverview>> {
        val response = apiService.getTickerOverview(ticker = ticker)
        val result = if (response.isSuccessful) {
            val tickerOverview = response.body()
            if (tickerOverview != null) {
                Result.success(tickerOverview)
            } else {
                Result.failure(Exception("Empty response body"))
            }
        } else {
            Result.failure(Exception("API request failed with code ${response.code()}"))
        }
        return flowOf(result)
    }
}