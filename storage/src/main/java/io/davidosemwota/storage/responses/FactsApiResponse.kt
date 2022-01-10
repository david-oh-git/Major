package io.davidosemwota.storage.responses

import androidx.annotation.Keep

@Keep
data class FactsApiResponse(
    val success: Success,
    val contents: Content
)
