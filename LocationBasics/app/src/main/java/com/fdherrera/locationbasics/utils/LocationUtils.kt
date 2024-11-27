package com.fdherrera.locationbasics.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Looper
import androidx.core.content.ContextCompat.checkSelfPermission
import com.fdherrera.locationbasics.LocationData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class LocationUtils(private val context: Context) {
    private val _fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(locationConsumer: (LocationData) -> Unit) {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { lastLocation ->
                    val location = LocationData(
                        latitude = lastLocation.latitude,
                        longitude = lastLocation.longitude,
                        address = reverseGeocodeLocation(lastLocation.latitude, lastLocation.longitude)
                    )
                    locationConsumer(location)
                }
            }
        }
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 1000
        ).build()

        _fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    fun hasLocationPermission(): Boolean {
        return checkPermission(ACCESS_COARSE_LOCATION)
                && checkPermission(ACCESS_FINE_LOCATION)
    }

    private fun reverseGeocodeLocation(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val coordinates = LatLng(latitude, longitude)
        val addresses = geocoder.getFromLocation(
            coordinates.latitude,
            coordinates.longitude,
            1
        )
        return if (addresses?.isNotEmpty() == true) {
            addresses[0].getAddressLine(0)
        } else {
            "Address not found"
        }
    }

    private fun checkPermission(manifestPermission: String): Boolean {
        return checkSelfPermission(context, manifestPermission) == PackageManager.PERMISSION_GRANTED
    }
}