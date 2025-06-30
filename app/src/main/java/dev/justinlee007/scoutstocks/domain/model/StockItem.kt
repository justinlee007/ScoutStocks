package dev.justinlee007.scoutstocks.domain.model

data class StockItem(
    val name: String,
    val ticker: String,
    val price: Double? = null,
    val change: Double? = null,
    val changePercent: Double? = null,
)
