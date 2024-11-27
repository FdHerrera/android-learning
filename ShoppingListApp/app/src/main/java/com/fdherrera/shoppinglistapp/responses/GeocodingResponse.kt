package com.fdherrera.shoppinglistapp.responses

import com.google.gson.annotations.SerializedName

data class GeocodingResponse(
    val results: List<GeocodingResult>,
    val status: String
)

data class GeocodingResult(
    @SerializedName("formatted_address") val formattedAddress: String
)