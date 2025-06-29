package dev.justinlee007.scoutstocks.data.source.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the response structure for the 'All Tickers' endpoint.
 */
@Serializable
data class TickerList(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("next_url")
    val nextUrl: String? = null,
    @SerialName("request_id")
    val requestId: String,
    @SerialName("results")
    val results: List<Ticker>? = null,
    @SerialName("status")
    val status: String,
)