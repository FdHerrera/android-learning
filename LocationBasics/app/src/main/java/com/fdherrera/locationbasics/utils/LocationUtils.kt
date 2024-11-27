package com.fdherrera.locationbasics.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat.checkSelfPermission

class LocationUtils(private val context: Context) {

    fun hasLocationPermission(): Boolean {
        return checkPermission(ACCESS_COARSE_LOCATION)
                && checkPermission(ACCESS_FINE_LOCATION)
    }

    private fun checkPermission(manifestPermission: String): Boolean {
        return checkSelfPermission(context, manifestPermission) == PackageManager.PERMISSION_GRANTED
    }
}