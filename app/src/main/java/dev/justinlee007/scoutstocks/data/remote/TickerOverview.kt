package dev.justinlee007.scoutstocks.data.remote

import kotlinx.serialization.SerialName

/**
 * Represents the response structure for the 'Ticker Overview' endpoint.
 */
data class TickerOverview(
    @SerialName("request_id")
    val requestId: String,
    @SerialName("results")
    val tickerResult: Ticker? = null,
    @SerialName("status")
    val status: String,
)