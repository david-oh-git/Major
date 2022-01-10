package io.davidosemwota.storage.responses

import androidx.annotation.Keep

@Keep
data class Content(
    val fact: String,
    val id: String,
    val category: String,
    val subcategory: String
)
