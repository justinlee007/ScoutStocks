/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.justinlee007.scoutstocks.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TickerDao {

    /**
     * Observes list of tickers.
     *
     * @return all tickers.
     */
    @Query("SELECT * FROM ticker")
    fun observeAll(): Flow<List<LocalTicker>>

    /**
     * Observes a single ticker.
     *
     * @param ticker the ticker symbol.
     * @return the ticker with the specified symbol.
     */
    @Query("SELECT * FROM ticker WHERE ticker = :ticker")
    fun observeByTicker(ticker: String): Flow<LocalTicker>

    /**
     * Select all tickers from the ticker table.
     *
     * @return all tickers.
     */
    @Query("SELECT * FROM ticker")
    suspend fun getAll(): List<LocalTicker>

    /**
     * Select a ticker by its symbol.
     *
     * @param ticker the ticker symbol.
     * @return the ticker with the specified symbol, or null if not found.
     */
    @Query("SELECT * FROM ticker WHERE ticker = :ticker")
    suspend fun getByTicker(ticker: String): LocalTicker?

    /**
     * Insert or update a ticker in the database. If a ticker already exists, replace it.
     *
     * @param task the ticker to be inserted or updated.
     */
    @Upsert
    suspend fun upsert(task: LocalTicker)

    /**
     * Delete a ticker by its symbol.
     */
    @Query("DELETE FROM ticker WHERE ticker = :ticker")
    suspend fun deleteByTicker(ticker: String): Int

    /**
     * Delete all tickers from the database.
     */
    @Query("DELETE FROM ticker")
    suspend fun deleteAll()
}
