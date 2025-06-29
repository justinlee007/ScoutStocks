package dev.justinlee007.scoutstocks.data.source.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the branding information for a ticker.
 */
@Serializable
data class Branding(
    @SerialName("logo_url")
    val logoUrl: String? = null,
    @SerialName("icon_url")
    val iconUrl: String? = null
)