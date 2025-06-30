package dev.justinlee007.scoutstocks.data.remote

import dev.justinlee007.scoutstocks.data.model.Ticker
import dev.justinlee007.scoutstocks.data.model.TickerList
import dev.justinlee007.scoutstocks.data.model.TickerOverview
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface for Polygon.io API endpoints.
 */
interface PolygonApiService {

    /**
     * Fetches a list of all tickers, with optional filtering and search.
     * Corresponds to: GET /v3/reference/tickers
     *
     * @param market Filter by market type (e.g., "stocks", "crypto"). Defaults to "stocks".
     * @param active Filter by actively traded assets (true/false).
     * @param search Search for terms within the ticker and/or company name.
     * @param limit Limit the number of results returned (default 100, max 1000).
     * @param sort Sort field used for ordering (e.g., "ticker", "name").
     * @param order Order results based on the sort field ("asc" or "desc").
     * @return A [TickerList] object containing a list of [Ticker] objects.
     */
    @GET("v3/reference/tickers")
    suspend fun getTickerList(
        @Query("market") market: String = "stocks",
        @Query("active") active: Boolean = true,
        @Query("search") search: String? = null,
        @Query("limit") limit: Int? = null, // Using nullable to allow API default
        @Query("sort") sort: String? = null,
        @Query("order") order: String? = null,
    ): Response<TickerList>

    /**
     * Fetches detailed information for a single ticker.
     * Corresponds to: GET /v3/reference/tickers/{ticker}
     *
     * @param ticker The ticker symbol of the asset (e.g., "AAPL").
     * @return A [TickerOverview] object which should contain a single [Ticker] in its results list.
     */
    @GET("v3/reference/tickers/{ticker}")
    suspend fun getTickerOverview(
        @Path("ticker") ticker: String,
    ): Response<TickerOverview>
}