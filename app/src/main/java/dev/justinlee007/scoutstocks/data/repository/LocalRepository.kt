package dev.justinlee007.scoutstocks.data.repository

import dev.justinlee007.scoutstocks.data.local.LocalTicker
import dev.justinlee007.scoutstocks.data.local.TickerDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing local data operations related to tickers.
 *
 * @property tickerDao The DAO for accessing ticker data.
 */
@Singleton
class LocalRepository @Inject constructor(
    private val tickerDao: TickerDao
) {

    /**
     * Observes all tickers in the database.
     */
    fun getAllTickers(): Flow<List<LocalTicker>> = tickerDao.observeAll()

    /**
     * Observes a single ticker by its symbol.
     *
     * @param ticker The ticker symbol to observe.
     */
    fun getTickerBySymbol(ticker: String): Flow<LocalTicker> = tickerDao.observeByTicker(ticker)

    /**
     * Inserts or updates a ticker in the database.
     *
     * @param ticker The ticker to be inserted or updated.
     */
    suspend fun upsertTicker(ticker: LocalTicker) {
        tickerDao.upsert(ticker)
    }

    /**
     * Deletes a ticker by its symbol.
     *
     * @param ticker The ticker symbol to delete.
     * @return The number of rows deleted.
     */
    suspend fun deleteTickerBySymbol(ticker: String): Int = tickerDao.deleteByTicker(ticker)
}