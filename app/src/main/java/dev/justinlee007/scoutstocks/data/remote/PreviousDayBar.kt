package dev.justinlee007.scoutstocks.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the response from the Polygon.io Previous Day Bar endpoint.
 * Corresponds to: GET /v2/aggs/ticker/{stocksTicker}/prev
 */
@Serializable
data class PreviousDayBar(
    @SerialName("ticker")
    val ticker: String? = null,
    @SerialName("queryCount")
    val queryCount: Int? = null,
    @SerialName("resultsCount")
    val resultsCount: Int? = null,
    @SerialName("adjusted")
    val adjusted: Boolean? = null,
    @SerialName("results")
    val results: List<AggregateBar>? = null,
    @SerialName("status")
    val status: String,
    @SerialName("request_id")
    val requestId: String,
    @SerialName("error")
    val error: String? = null,
    @SerialName("message")
    val message: String? = null
)
