package dev.justinlee007.scoutstocks.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the address information for a ticker.
 */
@Serializable
data class Address(
    @SerialName("address1")
    val address1: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("state")
    val state: String? = null,
    @SerialName("postal_code")
    val postalCode: String? = null
)