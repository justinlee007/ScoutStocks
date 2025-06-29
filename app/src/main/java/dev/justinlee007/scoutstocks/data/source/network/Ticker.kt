package dev.justinlee007.scoutstocks.data.source.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single stock ticker with detailed information.
 */
@Serializable
data class Ticker(
    @SerialName("ticker")
    val ticker: String,

    @SerialName("name")
    val name: String,

    @SerialName("market")
    val market: String,

    @SerialName("locale")
    val locale: String,

    @SerialName("active")
    val active: Boolean,

    @SerialName("currency_name")
    val currencyName: String? = null, // Can be null for some markets

    // Add other fields you might get from the API for a ticker, for example:
    @SerialName("cik")
    val cik: String? = null,

    @SerialName("composite_figi")
    val compositeFigi: String? = null,

    @SerialName("share_class_figi")
    val shareClassFigi: String? = null,

    @SerialName("last_updated")
    val lastUpdated: String? = null, // This might be a timestamp, consider using Instant/Long

    @SerialName("primary_exchange")
    val primaryExchange: String? = null,

    @SerialName("type")
    val type: String? = null,

    // Branding information (often nested)
    @SerialName("branding")
    val branding: Branding? = null,

    // Description, homepage, etc. (often part of the Ticker Overview response)
    @SerialName("description")
    val description: String? = null,
    @SerialName("homepage_url")
    val homepageUrl: String? = null,
    @SerialName("list_date")
    val listDate: String? = null,
    @SerialName("sic_code")
    val sicCode: String? = null,
    @SerialName("sic_description")
    val sicDescription: String? = null,
    @SerialName("total_employees")
    val totalEmployees: Int? = null,
    @SerialName("market_cap")
    val marketCap: Double? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null,
    @SerialName("address")
    val address: Address? = null, // Nested address object
)