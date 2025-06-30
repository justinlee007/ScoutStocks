package dev.justinlee007.scoutstocks.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AggregateBar(
    @SerialName("T") // Ticker
    val ticker: String? = null,
    @SerialName("v") // Volume
    val volume: Double,
    @SerialName("vw") // Volume Weighted Average Price
    val vw: Double? = null,
    @SerialName("o") // Open price
    val open: Double,
    @SerialName("c") // Close price
    val close: Double,
    @SerialName("h") // High price
    val high: Double,
    @SerialName("l") // Low price
    val low: Double,
    @SerialName("t") // Unix Millisecond Timestamp
    val timestamp: Long,
    @SerialName("n") // Number of trades in the aggregate
    val numberOfTrades: Int? = null
)