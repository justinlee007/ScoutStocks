package dev.justinlee007.scoutstocks.domain.model

data class StockDetail(
    val name: String,
    val ticker: String,
    // val historicalData: List<HistoricalData>,
    val volume: Double? = null,
    val marketCap: Double? = null,
)