package com.fdherrera.shoppinglistapp

import com.fdherrera.shoppinglistapp.responses.GeocodingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {
    @GET("maps/api/geocode/json")
    suspend fun getAddressFromCoordinates(
        @Query("latlng") latLng: String,
        @Query("key") apiKey: String
    ): GeocodingResponse
}