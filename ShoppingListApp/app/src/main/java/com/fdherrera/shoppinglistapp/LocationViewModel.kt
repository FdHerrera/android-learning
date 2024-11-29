package com.fdherrera.shoppinglistapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdherrera.shoppinglistapp.responses.GeocodingResult
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {
    private val _client = RetrofitClient.create()

    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _address = mutableStateOf<List<GeocodingResult>>(null)
    val address: State<List<GeocodingResult>> = _address

    fun updateLocation(newLocationData: LocationData) {
        _location.value = newLocationData
    }

    fun fetchAddress(locationData: LocationData) {
        try {
            viewModelScope.launch {
                val result = _client.getAddressFromCoordinates(
                    latLng = "",
                    apiKey = BuildConfig.MAPS_API_KEY
                )
                _address.value = result.results
            }
        } catch (e: Exception) {
            Log.e("res1", "Error fetching address: ${e.message}")
        }
    }
}